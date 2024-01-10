package com.testmall.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.testmall.Model.Commodities;
import com.testmall.Model.CommodityTag;
import com.testmall.Service.CommodityService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
public class ComController {
    @Autowired
    CommodityService comService;

    @GetMapping("/getComAll")
    @ResponseBody
    public List<Commodities> getComAll(){
        return comService.queryAll();
    }

    @GetMapping("/getComById")
    @ResponseBody
    public Commodities getComById(Long id){
        return comService.queryById(id);
    }

    @GetMapping("/getComByTag")
    @ResponseBody
    public List<Commodities> getComByTag(String tag){
        return comService.queryByTag(tag);
    }
}
