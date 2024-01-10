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
}
