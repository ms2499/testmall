package com.testmall.Controller;

import com.testmall.Model.CommodityTags;
import com.testmall.Service.ComTagsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//映射端點:Spring Boot應用，在Controller類中使用@RequestMapping註解來暴露REST API端點
//要获取这些端点，有三种选择：事件监听器、Spring Boot Actuator 或 SpringDoc.

//在 Controller 中使用 @RestController 和 @RequestMapping 创建 REST API 服务。
//这些类在 Spring Application Context 中注册为 Spring Bean。
//因此，当 Application Context 在启动时准备就绪，就可以使用事件监听器获取端点。
@RestController//此註解將 @Controller 和 @ResponseBody 注解合二为一，这会应用于该类中定义的所有端点。
@RequestMapping("/tag")//聲明請求的路徑和請求方法(使用此註解來暴露REST API端點)
@Tag(name = "商品種類相關API")
public class ComTagsController {
    @Autowired //它允许 Spring 解析并注入所依赖的 Bean 到 Bean 中。
    ComTagsService comTagsService;

    @GetMapping("/getAll") //用於定義HTTP GET請求的URL路徑
    //當客戶端發送HTTP GET 請求時，Spring Boot會自動將請求映射到具有相應路徑的控制器方法上。
    @ResponseBody //告訴Spring Boot, 返回值需要轉換為JSON或XML格式
    @Operation(summary = "查詢所有商品種類")
    public List<CommodityTags> queryAll(){
        return comTagsService.queryAll();
    }
    @PostMapping("/addTags")//用來聲明POST請求處理方法的註解
    //@PostMapping包含了@RequestMapping(聲明請求的路徑和請求方法)和@ResponseBody(用來告訴Spring Boot,返回值需要轉換為JSON或XML格式)
    @ResponseBody
    @Operation(summary = "新增商品種類")
    public String addCommodityTag (@RequestBody(required = false) CommodityTags tags){
        return comTagsService.addCommodityTag(tags);
    }
    @DeleteMapping("/deleteItem")//聲明能夠處理delete請求的方法
    @ResponseBody
    @Operation(summary = "刪除商品種類")
    public String deleteCommodityTag(@RequestBody(required = false) List<String> Tags){
        return comTagsService.deleteCommodityTag(Tags);
    }
    @PatchMapping("/updateTags")//對資料數據打補釘或局部更新
    @ResponseBody
    @Operation(summary = "更新商品種類")
    public String updateCommodityTag (@RequestBody(required = false) CommodityTags tags){
        return comTagsService.updateCommodityTag(tags);
    }
}
