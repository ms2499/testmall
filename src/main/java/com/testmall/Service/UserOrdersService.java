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

//    public String insertOrder(UserOrders userorders,OrderLists orderlists){
//        ordersDao.insertOrder(userorders);
//        return olService.insertList(orderlists);
//    }

    public String insertOrder(Carts carts){
        int aa;
        int no = Integer.parseInt(ordersDao.insertOrder(carts));
        if (no > 0){
            return olService.insertList(carts, no);
        }else{
            return "訂單新增失敗!";
        }
    }

    public String deleteOrder(List<Integer> no){
        int rowsAffected = 0;
        int failCount = 0;
        for (int number:no){
            if (ordersDao.deleteOrder(number) == 1){
                if (olService.deleteList(number) == 1){
                    rowsAffected++;
                }else {
                    failCount++;
                }
            }else {
                failCount++;
            }
        }
        return "共刪除"+no.size()+"筆, 成功"+rowsAffected+"筆, 失敗"+failCount+"筆!";
    }
}
