package com.testmall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testmall.Service.ShoppingCart;
import com.testmall.Model.Carts;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Carts item) {
        boolean added = shoppingCart.addItem(item);
        if (added){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeCartItem(@RequestParam Long productId) {
        boolean removed = shoppingCart.removeItem(productId);
        if (removed){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartItemQuantity(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        boolean updated = shoppingCart.updateQuantity(productId, quantity);
        if (updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the cart");
        }
    }

    @GetMapping("/view")
    public ResponseEntity<ShoppingCart> viewCart() {
        return ResponseEntity.ok(shoppingCart);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
    // 增加全域異常處理，處理未預期的異常情況
}
