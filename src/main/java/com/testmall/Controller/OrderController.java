package com.testmall.Controller;

import com.testmall.Model.Carts;
import com.testmall.Model.UserOrders;
import com.testmall.Model.OrderLists;
import com.testmall.Service.UserOrdersService;
import com.testmall.Service.OrderListsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserOrders> getOrderAll(){
        return orderService.queryAllOrders();
    }

    @GetMapping("/getByNo")
    @ResponseBody
    public UserOrders getOrderByNo(int no){
        return orderService.queryOrdersByNo(no);
    }

    @GetMapping("getByListNo")
    @ResponseBody
    public List<OrderLists> getListByNo(int no){
        return listService.queryListsByNo(no);
    }

    @PostMapping("/insertOrder")
    @ResponseBody
    public String insertOrder(@RequestBody(required = false) List<Carts> carts){
        return orderService.insertOrder(carts);
    }

    @PostMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestBody(required = false) List<Integer> no){
        return orderService.deleteOrder(no);
    }

    @PatchMapping("/updateOrder")
    @ResponseBody
    public String updateOrder(@RequestBody(required = false) UserOrders userorders){
        return orderService.updateOrder(userorders);
    }
}
