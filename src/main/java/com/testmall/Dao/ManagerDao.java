package com.testmall.Dao;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDao {
    @Autowired
    JdbcTemplate jt;
    CharsetTool cstool = new CharsetTool();

    public List<Manager> queryAll(){
        String sql = "SELECT * FROM manager;";

        try{
            List<Manager> managers =
                    jt.query(sql, (rs, n) -> new Manager(rs.getLong("ManID"),
                            rs.getString("ManAccount"),
                            rs.getString("ManPassword"),
                            rs.getString("ManSalt"),
                            rs.getString("ManName"),
                            rs.getString("ManPhone"),
                            rs.getString("ManEmail"),
                            rs.getString("ManAddress"),
                            rs.getString("ManMsg")));
            return managers;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Manager queryById(Long id){
        String sql = "SELECT * FROM manager WHERE ManID = " + id + ";";

        try{
            return jt.queryForObject(sql, (rs, n) -> new Manager(rs.getLong("ManID"),
                    rs.getString("ManAccount"),
                    rs.getString("ManPassword"),
                    rs.getString("ManSalt"),
                    rs.getString("ManName"),
                    rs.getString("ManPhone"),
                    rs.getString("ManEmail"),
                    rs.getString("ManAddress"),
                    rs.getString("ManMsg")));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Manager queryByAccount(String account){
        String sql = "SELECT * FROM manager WHERE ManAccount = '" + cstool.utf82iso(account) + "';";

        try{
            return jt.queryForObject(sql, (rs, n) -> new Manager(rs.getLong("ManID"),
                    rs.getString("ManAccount"),
                    rs.getString("ManPassword"),
                    rs.getString("ManSalt"),
                    rs.getString("ManName"),
                    rs.getString("ManPhone"),
                    rs.getString("ManEmail"),
                    rs.getString("ManAddress"),
                    rs.getString("ManMsg")));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String createManager(Manager manager){
        try {
            String sql = "INSERT INTO manager (ManAccount, ManPassword, ManSalt, ManName, " +
                    "ManPhone, ManEmail, ManAddress, ManMsg) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(manager.getManAccount()),
                    cstool.utf82iso(manager.getManPassword()),
                    cstool.utf82iso(manager.getManSalt()),
                    cstool.utf82iso(manager.getManName()),
                    cstool.utf82iso(manager.getManPhone()),
                    cstool.utf82iso(manager.getManEmail()),
                    cstool.utf82iso(manager.getManAddress()),
                    cstool.utf82iso(manager.getManMsg()));

            return "新增" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫新增失敗!";
        }
    }

    public String updateManager(Manager manager){
        try {
            String sql = "UPDATE manager SET ManAccount=?, ManPassword=?, ManSalt=?, ManName=?, "+
                    "ManPhone=?, ManEmail=?, ManAddress=?, ManMsg=? "+
                    "WHERE ManID = ?;";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(manager.getManAccount()),
                    cstool.utf82iso(manager.getManPassword()),
                    cstool.utf82iso(manager.getManSalt()),
                    cstool.utf82iso(manager.getManName()),
                    cstool.utf82iso(manager.getManPhone()),
                    cstool.utf82iso(manager.getManEmail()),
                    cstool.utf82iso(manager.getManAddress()),
                    cstool.utf82iso(manager.getManMsg()),
                    manager.getManID());
            return "更新" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫更新失敗!";
        }
    }

    public String deleteManager(List<Long> idList){
        try {
            String sql = "DELETE FROM manager WHERE ManID in(";
            StringBuilder str = new StringBuilder(sql);
            for (int i = 0; i < idList.size(); i++){
                str.append(idList.get(i));
                if (i == (idList.size() - 1))
                    str.append(");");
                else
                    str.append(",");
            }

            int rowsAffected = jt.update(str.toString());

            return "刪除" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫刪除失敗";
        }
    }

}
