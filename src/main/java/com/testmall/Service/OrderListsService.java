package com.testmall.Service;

import com.testmall.Dao.CommodityDao;
import com.testmall.Model.Carts;
import com.testmall.Model.Commodities;
import com.testmall.Model.OrderLists;
import com.testmall.Dao.OrderListsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListsService {
    @Autowired
    OrderListsDao listsDao;
    @Autowired
    CommodityDao comDao;

//應該用不到
//    public List<OrderLists> queryAllLists(){
//        return listsDao.queryAllLists();
//    }

    public OrderLists queryListsByNo(int no){
        return listsDao.queryListsByNo(no);
    }

//    public String insertList(OrderLists orderlists){
//        return listsDao.insertList(orderlists);
//    }
    public String insertList(Carts carts, int no) {
        int seq = 0;
        long id = carts.getCartCommodityID();
        int qty = carts.getCartQty();
//      long price = (comDao.queryById(id)).getCommodityPrice();
//      檢查 queryById 的結果是否為 null
        Commodities Commodities = comDao.queryById(id);
        long price = 0;
        if (Commodities == null) {
            return "商品ID不存在";
        } else {
            price = Commodities.getCommodityPrice();
        }
        int ret = 0;

        OrderLists list = new OrderLists(seq, no, id, qty, price, ret);
        return listsDao.insertList(list);
    }

    public int deleteList(Integer no){
        if (listsDao.deleteList(no) == 1){
            return 1;
        }else {
            return -1;
        }
    }
}