package com.testmall.Dao;

import com.testmall.Model.Carts;
import com.testmall.Model.OrderLists;
import com.testmall.Tools.CharsetTool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Repository
public class OrderListsDao {
    @Autowired
    JdbcTemplate jt;
    CharsetTool cstool = new CharsetTool();

    //應該用不到
    public List<OrderLists> queryAllLists(){
        String sql = "SELECT * FROM orderlists;";

        try{
            List<OrderLists> orderList =
                    jt.query(sql, (rs, n) -> new OrderLists(
                            rs.getInt("OrderSeq"),
                            rs.getInt("OrderNo"),
                            rs.getLong("OrderCommodityID"),
                            rs.getInt("OrderQty"),
                            rs.getInt("OrderPrice"),
                            rs.getInt("OrderReturn")));
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public OrderLists queryListsByNo(int no){
        String sql = "SELECT * FROM orderlists WHERE OrderNo = " + no + ";";

        try{
            return jt.queryForObject(sql, (rs, n) -> new OrderLists(
                    rs.getInt("OrderSeq"),
                    rs.getInt("OrderNo"),
                    rs.getLong("OrderCommodityID"),
                    rs.getInt("OrderQty"),
                    rs.getInt("OrderPrice"),
                    rs.getInt("OrderReturn")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String insertList(OrderLists orderlists){
        try {
            String sql = "INSERT INTO orderlists (OrderNo, OrderCommodityID, " +
                    "OrderQty, OrderPrice, OrderReturn) " +
                    "VALUES (?, ?, ?, ?, ?)";

            int rowsAffected = jt.update(sql,
                    orderlists.getOrderNo(),
                    orderlists.getOrderCommodityID(),
                    orderlists.getOrderQty(),
                    orderlists.getOrderPrice(),
                    orderlists.getOrderReturn());
            return "新增" + rowsAffected + "筆資料!";
        }catch (Exception e){
            e.printStackTrace();
            return "資料庫新增失敗!";
        }
    }

    public int deleteList(int no){
        try {
            String sql = "DELETE FROM orderlists WHERE OrderNo = ?";

            jt.update(sql, no);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
