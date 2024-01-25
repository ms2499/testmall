package com.testmall.Dao;

import com.testmall.Model.CommodityTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComTagsDao {
    @Autowired
    JdbcTemplate jt;

    public List<CommodityTags> queryAllMain(){
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

}
