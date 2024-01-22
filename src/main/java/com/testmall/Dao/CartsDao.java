package com.testmall.Dao;

import com.testmall.Model.Commodities;
import com.testmall.Model.Carts;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class CartsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CharsetTool cstool = new CharsetTool();
    public List<Carts> queryAll() {
        String sql = "SELECT * FROM carts";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Carts(
                    rs.getInt("CartSeq"),
                    rs.getString("CartAccount"),
                    rs.getLong("CartCommodityID"),
                    rs.getInt("CartQty")
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void removeCartItem(int cartSeq) {
        String sql = "DELETE FROM carts WHERE cartSeq = ?";
        int rowsAffected = jdbcTemplate.update(sql, cartSeq);
        // 實現從數據庫中刪除購物車項目的邏輯
        if (rowsAffected <= 0){
            throw new RuntimeException("No cart item found for the given cartSeq");
        }//錯誤處理
    }

    public void updateCartItemQuantity(int cartSeq, int quantity) {
        if (cartSeq == 0 || quantity <= 0){
            throw new IllegalArgumentException("Invalid cartSeq or quantity");
        }
        //參數校驗
        String sql = "UPDATE carts SET cartQty = ? WHERE cartSeq = ?";
        int rowsAffected = jdbcTemplate.update(sql, quantity, cartSeq);
        // 實現更新購物車項目數量到數據庫的邏輯
        if (rowsAffected <= 0){
            throw new RuntimeException("No cart item found for the given cartSeq");
        }//錯誤處理
    }
    // 其他可能的實現
}
