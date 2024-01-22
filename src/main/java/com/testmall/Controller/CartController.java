package com.testmall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testmall.Service.ShoppingCart;
import com.testmall.Model.Carts;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/queryAll")
    @ResponseBody
    public List<Carts> queryAll(){
        return shoppingCart.queryAll();
    }

    @PostMapping("/addCartItem")
    public ResponseEntity<String> addCartItem(@RequestBody Carts item) {
        boolean added = shoppingCart.addCartItem(item);
        if (added){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity<String> removeCartItem(@RequestParam int cartSeq) {
        boolean removed = shoppingCart.removeCartItem(cartSeq);
        if (removed){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @PutMapping("/updateCartItemQuantity")
    public ResponseEntity<String> updateCartItemQuantity(
            @RequestParam int cartSeq,
            @RequestParam int quantity) {
        boolean updated = shoppingCart.updateCartItemQuantity(cartSeq, quantity);
        if (updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the cart");
        }
    }

    @GetMapping("/viewCart")
    public ResponseEntity<ShoppingCart> viewCart() {
        return ResponseEntity.ok(shoppingCart);
    }
    //將購物車對象包裝在ResponseEntity中，並將其作為HTTP 200 OK的響應返回給客戶端。
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
    //例外發生時會回傳一個HTTP 500 Internal Server Error的響應，通知客戶端發生了內部伺服器錯誤。
    // 增加全域異常處理，處理未預期的異常情況(處理網頁異常)
}
