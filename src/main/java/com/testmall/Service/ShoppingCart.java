package com.testmall.Service;
//指明這個Java檔案屬於com.testmall.Service包（package）
import com.testmall.Dao.CartsDao;
import com.testmall.Model.Carts;
import org.springframework.beans.factory.annotation.Autowired;
//導入了com.testmall.Model包中的CartItem類別，表示這個類別會使用到CartItem
import java.util.ArrayList;
import java.util.List;
//導入了 Java 標準庫中的ArrayList和List類別，這兩個類別是用來處理動態陣列和列表的

public class ShoppingCart {
//定義ShoppingCart類別，宣告了一個名為ShoppingCart的類別
    @Autowired
    private CartsDao cartsDao;

    //Getters and setters
    public boolean addItem(Carts item) {
        // 實現添加商品的邏輯
        //新增商品需要進行資料庫操作，所以要去call cartsDao裡的方法
        try {
            cartsDao.saveCartItem(item);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
        // 返回添加商品的結果
    }

    public boolean removeItem(Long productId) {
        // 實現刪除商品的邏輯
        //同新增，call cartsDao的方法操作資料庫
        try {
            cartsDao.removeCartItem(productId);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        // 返回刪除商品的結果
    }

    public boolean updateQuantity(Long productId, int quantity) {
        // 實現更新商品數量的邏輯，根據商品編號和數量更新購物車中相應的CartItem的數量
        //同新增，call cartsDao的方法操作資料庫
        try {
            cartsDao.updateCartItemQuantity(productId, quantity);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
        // 返回更新商品數量的結果
    }

    public List<Carts> getItems() {
    /*用來獲取購物車中的所有CartItem
    它直接返回items列表，這樣外部類別就可以獲取整個購物車的內容
     */
        //這邊也是要去call cartsDao的方法去查詢資料庫然後返回結果
        //目前CartsDao還沒有查資料的方法，這邊先舉例
        try {
//到時候記得拿掉註解            return cartsDao.queryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void setCartItemDao(CartsDao cartsDao) {
        this.cartsDao = cartsDao;
    }
    //屬性注入
    //ShoppingCart定義了一個setCartItemDao方法，通過這個方法可以在外部設置CartItemDao
}