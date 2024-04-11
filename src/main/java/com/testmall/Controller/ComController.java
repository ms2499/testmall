package com.testmall.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.testmall.Model.Commodities;
import com.testmall.Service.CommodityService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/com")
@Tag(name = "商品相關API")
public class ComController {
    @Autowired
    CommodityService comService;

    @GetMapping("/getAll")
    @ResponseBody
    @Operation(summary = "取得所有商品資料")
    public List<Commodities> getAll(){
        return comService.queryAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    @Operation(summary = "查詢商品(By ID)")
    public Commodities getById(@Parameter(description = "商品ID") @RequestParam Long id){
        return comService.queryById(id);
    }

    @GetMapping("/getByTag")
    @ResponseBody
    @Operation(summary = "查詢商品(By 種類)")
    public List<Commodities> getByTag(@Parameter(description = "商品ID") @RequestParam String tag){
        return comService.queryByTag(tag);
    }

    @GetMapping("/search")
    @ResponseBody
    @Operation(summary = "模糊查詢商品")
    public List<Commodities> search(@Parameter(description = "關鍵字") @RequestParam String key){
        return comService.search(key);
    }

    @GetMapping("getDiscountCom")
    @ResponseBody
    @Operation(summary = "查詢優惠商品")
    public List<Commodities> getDiscountCom(){
        return comService.queryDiscountCom();
    }

    @GetMapping("getByRow")
    @ResponseBody
    @Operation(summary = "依行數查詢")
    public List<Commodities> getByRow(@Parameter(description = "SQLMX:上次查詢的最後一筆commodityID, 其他SQL:頁數") @RequestParam Long key,
                                            @Parameter(description = "要查幾行") @RequestParam int rowNum){
        return comService.queryByRow(key, rowNum);
    }

    @GetMapping("getRowCount")
    @ResponseBody
    @Operation(summary = "查詢商品總筆數")
    public Long getRowCount(){
        return comService.queryRowCount();
    }

    @PostMapping("/insertItem")
    @ResponseBody
    @Operation(summary = "新增商品")
    public String insertItem(@RequestBody Commodities commodities){
        return comService.insertItem(commodities);
    }

    @PatchMapping("/updateItem")
    @ResponseBody
    @Operation(summary = "更新商品")
    public String updateItem(@RequestBody(required = false) Commodities commodities){
        return comService.updateItem(commodities);
    }

    @DeleteMapping("/deleteItem")
    @ResponseBody
    @Operation(summary = "刪除商品")
    public String deleteItem(@RequestBody(required = false) List<Long> idList){
        return comService.deleteItem(idList);
    }
}
