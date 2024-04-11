package com.testmall.Service;

import com.testmall.Dao.CommodityDao;
import com.testmall.Model.Commodities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService {
    @Autowired
    CommodityDao comDao;

    public List<Commodities> queryAll(){
        return comDao.queryAll();
    }

    public Commodities queryById(Long id){
        return comDao.queryById(id);
    }

    public List<Commodities> queryByTag(String tag){
        return comDao.queryByTag(tag);
    }

    public List<Commodities> search(String key) {
        return comDao.search(key);
    }

    public List<Commodities> queryDiscountCom() {
        return comDao.queryDiscountCom();
    }

    public List<Commodities> queryByRow(Long key, int rowNum) {
        return comDao.queryByRow(key, rowNum);
    }

    public Long queryRowCount() {
        return comDao.queryRowCount();
    }

    public String insertItem(Commodities commodities){
        return comDao.insertItem(commodities);
    }

    public String updateItem(Commodities commodities){
        return comDao.updateItem(commodities);
    }

    public String deleteItem(List<Long> idList){
        return comDao.deleteItem(idList);
    }
}
