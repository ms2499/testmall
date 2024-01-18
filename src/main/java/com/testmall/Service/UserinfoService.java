package com.testmall.Service;

import com.testmall.Dao.UserinfoDao;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoService {
    @Autowired
    UserinfoDao userDao;

    public List<Userinfo> queryAll(){
        return userDao.queryAll();
    }

    public Userinfo queryByAccount(String account){
        return userDao.queryByAccount(account);
    }

    public String createUser(Userinfo userinfo){
        return userDao.createUser(userinfo);
    }

    public String updateUser(Userinfo userinfo){
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

}
