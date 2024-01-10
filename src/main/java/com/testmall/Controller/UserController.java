package com.testmall.Controller;

import com.testmall.Model.Commodity;
import com.testmall.Model.Userinfo;
import com.testmall.Service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserinfoService userService;

    @GetMapping("/getByAccount")
    @ResponseBody
    public Userinfo getByAccount(@RequestParam String account){
        return userService.queryUserByAccount(account);
    }
}
