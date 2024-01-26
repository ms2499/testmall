package com.testmall.Dao;

import com.testmall.Model.CommodityTags;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComTagsDao {
    @Autowired
    JdbcTemplate jt;

    CharsetTool cstool = new CharsetTool();

    //查詢小類+大類
    public List<CommodityTags> queryAll(){
        String sql = "SELECT DISTINCT CommoditySubTag, CommodityMainTag FROM commodity_tags;";

        try{
            return jt.query(sql, (rs, n) -> new CommodityTags(
                    rs.getString("CommoditySubTag"),
                    rs.getString("CommodityMainTag")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //新增小類
    public String addCommoditySubTag(CommodityTags SubTag) {
        try {
            String sql ="INSERT INTO commodity_tags(CommitySubTag) VALUES (?)";
            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(SubTag.getCommoditySubTag()));
            return "新增" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.insertItem");
            return "資料庫新增失敗!";
        }
    }
}