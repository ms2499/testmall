package com.testmall.Dao;

import com.testmall.Model.Commodities;
import com.testmall.Model.Userinfo;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserinfoDao {
    @Autowired
    JdbcTemplate jt;
    CharsetTool cstool = new CharsetTool();

    public Userinfo queryUserByAccount(String account){
        String sql = "SELECT * FROM userinfo WHERE UserAccount = '" + cstool.utf82iso(account) + "';";

        try{
            Userinfo user =
                jt.queryForObject(sql, (rs, n) -> new Userinfo(rs.getString("UserAccount"),
                        rs.getString("UserPassword"),
                        rs.getString("UserName"),
                        rs.getString("UserPhone"),
                        rs.getString("UserEmail"),
                        rs.getString("UserAddress"),
                        rs.getString("UserMsg")));
            cstool.pLog(user.getUserAccount()+" ");
            cstool.pLogln(user.getUserName());
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
