package com.testmall.Service;

import com.testmall.Dao.UserOrdersDao;
import com.testmall.Model.Carts;
import com.testmall.Model.UserOrders;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrdersService {
    @Autowired
    UserOrdersDao ordersDao;
    @Autowired
    OrderListsService olService;

    public List<UserOrders> queryAllOrders(){
        return ordersDao.queryAllOrders();
    }

    public UserOrders queryOrdersByNo(int no){
        return ordersDao.queryOrdersByNo(no);
    }

    public String insertOrder(List<Carts> carts){
//      int no = ordersDao.insertOrder((Carts) carts);
        int no = ordersDao.insertOrder(carts.get(0));
        long total = 0;
        if (no > 0) {
            for (Carts cart:carts) {
                long price = olService.insertList(cart, no);
                total = total + price;
            }
            //回填訂單總金額
            if(total > 0) {
                ordersDao.updateOrderTotal(no, total);
            }
            return "新增訂單成功!";
        } else {
            return "新增訂單失敗!";
        }
    }

    public String insertOrderBackend(UserOrders userorders){
        return ordersDao.insertOrderBackend(userorders);
    }

    public String deleteOrder(List<Integer> no){
        int rowsAffected = 0;
        int failCount = 0;
        for (int number:no){
            if (ordersDao.deleteOrder(number) == 1){
//由DB連動,這邊不需處裡
//                if (olService.deleteList(number) == 1){
//                    rowsAffected++;
//                }else {
//                    failCount++;
//                }
                rowsAffected++;
            }else {
                failCount++;
            }
        }
        return "共刪除"+no.size()+"筆, 成功"+rowsAffected+"筆, 失敗"+failCount+"筆!";
    }

    public String updateOrder(UserOrders userorders){
        return ordersDao.updateOrder(userorders);
    }
}
