package com.testmall.Dao;

import com.testmall.Model.Carts;
import com.testmall.Model.UserOrders;
import com.testmall.Tools.CharsetTool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserOrdersDao {
    @Autowired
    JdbcTemplate jt;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    CharsetTool cstool = new CharsetTool();

    public List<UserOrders> queryAllOrders(){
        String sql = "SELECT * FROM userorders;";

        try{
            List<UserOrders> orderList =
                    jt.query(sql, (rs, n) -> new UserOrders(
                    rs.getInt("OrderNo"),
                    rs.getString("OrderAccount"),
                    rs.getTimestamp("OrderDate"),
                    rs.getInt("OrderTotal")));
//            for (UserOrders c:orderList) {
//                cstool.pLogln(c.getOrderAccount());
//                cstool.pLogln(c.getOrderDate());
//            }
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查詢失敗: " + e.getMessage());
        }
    }

    public UserOrders queryOrdersByNo(int no){
        String sql = "SELECT * FROM userorders WHERE OrderNo = " + no + ";";

        try{
            return jt.queryForObject(sql, (rs, n) -> new UserOrders(
                    rs.getInt("OrderNo"),
                    rs.getString("OrderAccount"),
                    rs.getTimestamp("OrderDate"),
                    rs.getInt("OrderTotal")));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查詢失敗: " + e.getMessage());
        }
    }

    public Integer insertOrder(Carts carts){
        try {
            // 創建 GeneratedKeyHolder 對象
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO userorders (OrderAccount, OrderDate) VALUES(:account, :date);";

            // 使用 Map 封裝參數，key 就是 SQL 中對應的參數名稱
            Map<String, Object> params = new HashMap<>();
            params.put("account", cstool.utf82iso(carts.getCartAccount()));
//          params.put("date", cstool.utf82iso(String.valueOf(LocalDateTime.now())));
            params.put("date", Timestamp.valueOf(LocalDateTime.now()));

            // 執行更新，返回受影響行數
            int retVal = this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);

            // 通過 keyHolder 獲取到自增值
            return keyHolder.getKey().intValue();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("新增失敗: " + e.getMessage());
        }
    }

    public int deleteOrder(int no){
        try {
            String sql = "DELETE FROM userorders WHERE OrderNo = ?";

            jt.update(sql, no);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public String updateOrderTotal(int no, long total){
        try {
            String sql = "UPDATE userorders SET OrderTotal=? WHERE OrderNo = ?;";

            int rowsAffected = jt.update(sql, total, no);

            return "更新" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "UserOrdersDao.updateOrderTotal");
            return "訂單總價計算錯誤!";
        }
    }

    public String updateOrder(UserOrders userorders){
        try {
            String sql = "UPDATE userorders SET OrderAccount=?, OrderDate=?, OrderTotal=? "+
                         "WHERE OrderNo = ?;";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(userorders.getOrderAccount()),
                    userorders.getOrderDate(),
                    userorders.getOrderTotal(),
                    userorders.getOrderNo());
            return "更新" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "UserOrdersDao.updateItem");
            return "資料庫更新失敗!";
        }
    }
}
