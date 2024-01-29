package com.testmall.Dao;

import com.testmall.Model.Commodities;
import com.testmall.Model.Carts;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class CartsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CharsetTool cstool = new CharsetTool();
    /*2024-01-26修正*/
    public List<Carts> queryAll() throws IllegalArgumentException {
        String sql = "SELECT * FROM carts";
        List<Carts> result = jdbcTemplate.query(sql, (rs, rowNum) -> new Carts(
                rs.getInt("CartSeq"),
                rs.getString("CartAccount"),
                rs.getLong("CartCommodityID"),
                rs.getInt("CartQty")
        ));
        if (result.isEmpty()) {
            throw new IllegalArgumentException("The cart is empty.");
        }

        return result;
    }
    /*2024-01-26原用try&catch,改為throw*/
    /*
    public List<Carts> qureAll() {
        return cartsList;
    }

    // 查询 carts 信息并根据条件抛出异常的方法
    public void queryCartsInfo(int cartSeq, String cartAccount, Long cartCommodityID, int cartQty) throws CartQueryException {
        List<Carts> queriedCarts = qureAll();
        for (Carts cart : queriedCarts) {
            // 判断条件是否符合
            if (cart.getCartSeq() == cartSeq && cart.getCartAccount().equals(cartAccount)
                    && cart.getCartCommodityID() == cartCommodityID && cart.getCartQty() == cartQty) {
                // 符合条件，返回查询结果
                System.out.println("Cart found: " + cart);
                return;
            }
        }
        // 条件不符合，抛出异常
        throw new CartQueryException("Cart not found with given criteria: CartSeq=" + cartSeq +
                ", CartAccount=" + cartAccount + ", CartCommodityID=" + cartCommodityID + ", CartQty=" + cartQty);
    }
}
*/

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
