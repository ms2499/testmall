package com.testmall.Controller;

import com.testmall.Model.Carts;
import com.testmall.Model.UserOrders;
import com.testmall.Model.OrderLists;
import com.testmall.Service.UserOrdersService;
import com.testmall.Service.OrderListsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/order")
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
    public OrderLists getListByNo(int no){
        return listService.queryListsByNo(no);
    }

//    @PostMapping("/insertOrder")
//    @ResponseBody
//    public String insertOrder(@RequestBody(required = false) UserOrders userorders, OrderLists orderlists){
//        return orderService.insertOrder(userorders, orderlists);
//    }

    @PostMapping("/insertOrder")
    @ResponseBody
    public String insertOrder(@RequestBody(required = false) List<Carts> carts){
        StringBuilder result = new StringBuilder();
        for (Carts cart:carts){
            String insertionResult = orderService.insertOrder(cart);
            result.append(insertionResult).append("\n");
//          return "共"+carts.size()+"筆";
        }
        return result.toString();
    }

    @PostMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestBody(required = false) List<Integer> no){
        return orderService.deleteOrder(no);
    }
}
