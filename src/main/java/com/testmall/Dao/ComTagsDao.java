package com.testmall.Dao;

import com.testmall.Model.Carts;
import com.testmall.Model.CommodityTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComTagsDao {
    @Autowired
    JdbcTemplate jt;

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
    public void addCommoditySubTag(CommodityTags SubTag) {
        if (SubTag == null) {
            throw new IllegalArgumentException("Invalid subtag data");
        }
        else ;
    }
}

/*
    public void addCartItem(Carts item) {
        if (item == null || item.getCartQty() <= 0) {
            throw new IllegalArgumentException("Invalid cart item data");
        }
        //參數校驗
        String sql = "INSERT INTO carts (cartAccount, cartCommodityID, cartQty) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql,
                cstool.utf82iso(item.getCartAccount()),
                item.getCartCommodityID(),
                item.getCartQty());
        // 實現保存購物車項目到數據庫的邏輯，使用jdbcTemplate執行SQL語句
        if (rowsAffected <= 0) {
            throw new RuntimeException("Failed to add cart item");
        }//錯誤處理
    }
}
*/