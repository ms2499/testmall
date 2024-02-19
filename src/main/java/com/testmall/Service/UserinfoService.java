package com.testmall.Service;

import com.testmall.Dao.UserinfoDao;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import com.testmall.Tools.CharsetTool;
import com.testmall.Tools.MD5util;
import com.testmall.Tools.UUIDutil;
import com.testmall.properties.CustomProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class UserinfoService {
    CharsetTool cstool = new CharsetTool();
    @Autowired
    UserinfoDao userDao;

    public List<Userinfo> queryAll(){
        return userDao.queryAll();
    }

    public Userinfo queryByAccount(String account){
        return userDao.queryByAccount(account);
    }

    public String createUser(Userinfo userinfo){
        if (userDao.queryByAccount(userinfo.getUserAccount())!=null)
            return "此帳號已存在!";
        else{
            String salt = UUIDutil.uuid();
            userinfo.setUserSalt(salt);
            String md5Password = MD5util.md5(userinfo.getUserPassword(), salt);
            userinfo.setUserPassword(md5Password);
            return userDao.createUser(userinfo);
        }
    }

    public String updateUser(Userinfo userinfo){
        Userinfo oldUser = userDao.queryByAccount(userinfo.getUserAccount());
        if (oldUser == null){
            cstool.pLogln("查無此帳號"+userinfo.getUserAccount(), "UserinfoService.updateUser");
            return "查無此帳號!";
        }
        if (!oldUser.getUserPassword().equals(userinfo.getUserPassword())){
            String salt = UUIDutil.uuid();
            userinfo.setUserSalt(salt);
            String md5Password = MD5util.md5(userinfo.getUserPassword(), salt);
            userinfo.setUserPassword(md5Password);
        }
        return userDao.updateUser(userinfo);
    }

    public String deleteUser(List<String> accounts){
        int rowsAffected = 0;
        int failCount = 0;
        for (String account:accounts){
            if (userDao.deleteUser(account) == 1){
                rowsAffected++;
            }else {
                failCount++;
            }
        }
        return "共刪除"+accounts.size()+"筆, 成功"+rowsAffected+"筆, 失敗"+failCount+"筆!";
    }

    public String userLogin(String account, String password){
        Userinfo user = userDao.queryByAccount(account);
        String result = "9999";
        if (user != null){
            String salt = user.getUserSalt().trim();
            String md5Password = MD5util.md5(password, salt);
            if (user.getUserPassword().equals(md5Password)){
                //設定30min過期
                Date expireDate = new Date(System.currentTimeMillis()+30*60*1000);
                SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(CustomProperty.secretKey));
                result = Jwts.builder()
                        .subject(account) //以account當subject
                        .expiration(expireDate)
                        .signWith(secretKey)
                        .compact();
            }else{
                result = "0002"; //密碼錯誤
            }
        }else{
            result = "0001"; //查無帳號
        }
        return result;
    }

    public String verifyUser(String jwt){
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(CustomProperty.secretKey));
            Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt);
            Claims claimsPayload = claimsJws.getPayload();
            String account = claimsPayload.getSubject();
            return "0000";
        }catch (SignatureException se) {
            cstool.pLogln(se.toString(), "UserinfoService.verifyUser");
            return "0001"; //驗證失敗
        }catch (ExpiredJwtException ee){
            cstool.pLogln(ee.toString(), "UserinfoService.verifyUser");
            return "0002"; //Token過期
        }catch (Exception e){
            cstool.pLogln(e.toString(), "UserinfoService.verifyUser");
            return "9999"; //未知錯誤
        }
    }
}
