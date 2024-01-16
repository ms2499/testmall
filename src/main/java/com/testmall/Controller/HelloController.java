package com.testmall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/CommodityManager")
    public String CommodityManager() {
        return "CommodityManager";
    }

    @GetMapping("/CommodityTagManager")
    public String CommodityTagManager() {
        return "CommodityTagManager";
    }

    @GetMapping("/UserinfoManager")
    public String UserinfoManager() {
        return "UserinfoManager";
    }

    @GetMapping("/CartManager")
    public String CartManager() {
        return "CartManager";
    }

    @GetMapping("/OrderManager")
    public String OrderManager() {
        return "OrderManager";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello 測試";
    }
}
