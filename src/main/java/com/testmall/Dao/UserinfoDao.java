package com.testmall.Dao;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
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

    public List<Userinfo> queryAll(){
        String sql = "SELECT * FROM userinfo;";

        try{
            List<Userinfo> userinfo =
                    jt.query(sql, (rs, n) -> new Userinfo(rs.getString("UserAccount"),
                            rs.getString("UserPassword"),
                            rs.getString("UserSalt"),
                            rs.getString("UserName"),
                            rs.getString("UserPhone"),
                            rs.getString("UserEmail"),
                            rs.getString("UserAddress"),
                            rs.getString("UserMsg")));
            return userinfo;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Userinfo queryByAccount(String account){
        String sql = "SELECT * FROM userinfo WHERE UserAccount = '" + cstool.utf82iso(account) + "';";

        try{
            Userinfo user =
                jt.queryForObject(sql, (rs, n) -> new Userinfo(rs.getString("UserAccount"),
                        rs.getString("UserPassword"),
                        rs.getString("UserSalt"),
                        rs.getString("UserName"),
                        rs.getString("UserPhone"),
                        rs.getString("UserEmail"),
                        rs.getString("UserAddress"),
                        rs.getString("UserMsg")));
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String createUser(Userinfo userinfo){
        try {
            String sql = "INSERT INTO userinfo (UserAccount, UserPassword, UserSalt, UserName, " +
                    "UserPhone, UserEmail, UserAddress, UserMsg) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(userinfo.getUserAccount()),
                    cstool.utf82iso(userinfo.getUserPassword()),
                    cstool.utf82iso(userinfo.getUserSalt()),
                    cstool.utf82iso(userinfo.getUserName()),
                    cstool.utf82iso(userinfo.getUserPhone()),
                    cstool.utf82iso(userinfo.getUserEmail()),
                    cstool.utf82iso(userinfo.getUserAddress()),
                    cstool.utf82iso(userinfo.getUserMsg()));

            return "新增" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫新增失敗!";
        }
    }

    public String updateUser(Userinfo userinfo){
        try {
            String sql = "UPDATE userinfo SET UserAccount=?, UserPassword=?, UserSalt=?, UserName=?, "+
                    "UserPhone=?, UserEmail=?, UserAddress=?, UserMsg=? "+
                    "WHERE UserAccount = ?;";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(userinfo.getUserAccount()),
                    cstool.utf82iso(userinfo.getUserPassword()),
                    cstool.utf82iso(userinfo.getUserSalt()),
                    cstool.utf82iso(userinfo.getUserName()),
                    cstool.utf82iso(userinfo.getUserPhone()),
                    cstool.utf82iso(userinfo.getUserEmail()),
                    cstool.utf82iso(userinfo.getUserAddress()),
                    cstool.utf82iso(userinfo.getUserMsg()),
                    cstool.utf82iso(userinfo.getUserAccount()));

            return "更新" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫更新失敗!";
        }
    }

    public int deleteUser(String accounts){
        try {
            String sql = "DELETE FROM userinfo WHERE UserAccount = ?";

            jt.update(sql, cstool.utf82iso(accounts));

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
