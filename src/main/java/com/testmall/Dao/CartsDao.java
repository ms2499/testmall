package com.testmall.Dao;

import com.testmall.Model.Commodity;
import com.testmall.Model.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.testmall.Model.Carts;

import java.util.Objects;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class CartsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Commodity getCommodityByCartItemId(Long cartCommodityID) {
        Objects.requireNonNull(cartCommodityID, "購物車商品ID不能為空!");

        String sql = "SELECT * FROM commodities WHERE CommodityID = ?";
        try {
            // 使用BeanPropertyRowMapper自动将结果集映射到Commodity类
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Commodity.class), cartCommodityID);
        } catch (Exception e) {
            // 根据需要处理异常（例如，如果找不到给定cartCommodityID的商品）
            throw new RuntimeException("查詢購物車商品詳細訊息時出錯，商品ID：" + cartCommodityID, e);
        }
    }
    public void saveCartItem(Carts item) {
        if (item == null || item.getCartQty() <= 0) {
            throw new IllegalArgumentException("Invalid cart item data");
        }
        //參數校驗
        String sql = "INSERT INTO cart_items (cartAccount, cartCommodityID, cartQty) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, item.getCartAccount(), item.getCartCommodityID(), item.getCartQty());
        // 實現保存購物車項目到數據庫的邏輯，使用jdbcTemplate執行SQL語句
        if (rowsAffected <= 0) {
            throw new RuntimeException("Failed to save cart item");
        }//錯誤處理
    }

    public void removeCartItem(Long productId) {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        //參數校驗
        String sql = "DELETE FROM carts WHERE cartCommodityID = ?";
        int rowsAffected = jdbcTemplate.update(sql, productId);
        // 實現從數據庫中刪除購物車項目的邏輯
        if (rowsAffected <= 0){
            throw new RuntimeException("No cart item found for the given product ID");
        }//錯誤處理
    }

    public void updateCartItemQuantity(Long productId, int quantity) {
        if (productId == null || quantity <= 0){
            throw new IllegalArgumentException("Invalid product ID or quantity");
        }
        //參數校驗
        String sql = "UPDATE carts SET cartQty = ? WHERE cartCommodityID = ?";
        int rowsAffected = jdbcTemplate.update(sql, quantity, productId);
        // 實現更新購物車項目數量到數據庫的邏輯
        if (rowsAffected <= 0){
            throw new RuntimeException("No cart item found for the given product ID");
        }//錯誤處理
    }
    // 其他可能的實現
}
