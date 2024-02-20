package com.testmall.Service;

import com.testmall.Dao.ComTagsDao;
import com.testmall.Dao.CommodityDao;
import com.testmall.Model.Commodities;
import com.testmall.Model.CommodityTags;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//↓[public class=程式功能]這段程式碼定義了一個名為 ShoppingCart 的類別
public class ComTagsService {
    @Autowired
    //↓[private=工具]包含一個私有成員變數 comTagsDao，用於處理商品類別相關的資料庫訪問操作。
    ComTagsDao comTagsDao;
    //查詢
    public List<CommodityTags> queryAll(){
        return comTagsDao.queryAll();
    }
    //新增
    public String addCommodityTag(CommodityTags tags){
        return comTagsDao.addCommodityTag(tags);
    }
    //刪除
    public String deleteCommodityTag(List<String> tags){
        return comTagsDao.deleteCommodityTag(tags);
    }
    //更新(=)
    public String updateCommodityTag(CommodityTags tags){
        return comTagsDao.updateCommodityTag(tags);
    }

}
