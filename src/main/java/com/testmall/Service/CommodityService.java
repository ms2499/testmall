package com.testmall.Service;

import com.testmall.Dao.CommodityDao;
import com.testmall.Model.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService {
    @Autowired
    CommodityDao comDao;

    public List<Commodity> queryAll(){
        return comDao.queryAll();
    }

    public Commodity queryById(Long id){
        return comDao.queryById(id);
    }

    public List<Commodity> queryByTag(String tag){
        return comDao.queryByTag(tag);
    }
}
