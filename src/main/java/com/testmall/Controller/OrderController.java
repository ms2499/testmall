package com.testmall.Controller;

import com.testmall.Model.Carts;
import com.testmall.Model.UserOrders;
import com.testmall.Model.OrderLists;
import com.testmall.Service.UserOrdersService;
import com.testmall.Service.OrderListsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "訂單相關API")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
    @Autowired
    UserOrdersService orderService;
    @Autowired
    OrderListsService listService;

    @GetMapping("/getAll")
    @ResponseBody
    @Operation(summary = "取得所有訂單資料")
    public List<UserOrders> getOrderAll(){
        return orderService.queryAllOrders();
    }

    @GetMapping("/getByNo")
    @ResponseBody
    @Operation(summary = "查詢使用者訂單資料(By 訂單編號)")
    public UserOrders getOrderByNo(int no){
        return orderService.queryOrdersByNo(no);
    }

    @GetMapping("getByListNo")
    @ResponseBody
    @Operation(summary = "查詢訂單詳細資料(By 訂單編號)")
    public List<OrderLists> getListByNo(int no){
        return listService.queryListsByNo(no);
    }

    @GetMapping("getNoByAccount")
    @ResponseBody
    @Operation(summary = "查詢訂單編號(By 帳號)")
    public List<Integer> getNoByAccount(String account){
        List<Integer> nos = orderService.getNoByAccount(account);
        return nos;
    }

    @PostMapping("/insertOrder")
    @ResponseBody
    @Operation(summary = "新增訂單")
    public String insertOrder(@RequestBody(required = false) List<Carts> carts){
        return orderService.insertOrder(carts);
    }

    @PostMapping("/deleteOrder")
    @ResponseBody
    @Operation(summary = "刪除訂單")
    public String deleteOrder(@RequestBody(required = false) List<Integer> no){
        return orderService.deleteOrder(no);
    }

    @PatchMapping("/updateOrder")
    @ResponseBody
    @Operation(summary = "更新訂單")
    public String updateOrder(@RequestBody(required = false) UserOrders userorders){
        return orderService.updateOrder(userorders);
    }
}
