package com.testmall.Service;

import com.testmall.Dao.ComTagsDao;
import com.testmall.Model.Carts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//↓[public class=程式功能]這段程式碼定義了一個名為 ShoppingCart 的類別
public class ComTagsService {
    @Autowired
    //↓[private=工具]包含一個私有成員變數 comTagsDao，用於處理商品類別相關的資料庫訪問操作。
    private ComTagsDao comTagsDao;
    //查詢
    public List<Carts> queryAll(){
        try{
            return ComTagsDao.queryAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //新增

    //刪除
    //更新(=)

}
