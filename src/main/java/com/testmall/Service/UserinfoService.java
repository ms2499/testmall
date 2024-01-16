package com.testmall.Service;

import com.testmall.Dao.UserinfoDao;
import com.testmall.Model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoService {
    @Autowired
    UserinfoDao userDao;

    public Userinfo queryByAccount(String account){
        return userDao.queryByAccount(account);
    }
}
