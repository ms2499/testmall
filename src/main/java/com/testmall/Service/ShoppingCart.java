package com.testmall.Service;
//指明這個Java檔案屬於com.testmall.Service包（package）
import com.testmall.Dao.CartsDao;
import com.testmall.Model.Carts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
//導入了 Java 標準庫中的List類別，是用來處理動態陣列和列表的

// 2024-01-22新增 @Service
@Service
public class ShoppingCart {
//定義ShoppingCart類別，宣告了一個名為ShoppingCart的類別
    @Autowired
    private CartsDao cartsDao;

    //Getters and setters
    public List<Carts> queryAll(){
        try{
            return cartsDao.queryAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCartItem(Carts item) {
        // 實現添加商品的邏輯
        //新增商品需要進行資料庫操作，所以要去call cartsDao裡的方法
        try {
            cartsDao.addCartItem(item);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
        // 返回添加商品的結果
    }

    public boolean removeCartItem(int cartSeq) {
        // 實現刪除商品的邏輯
        //同新增，call cartsDao的方法操作資料庫
        try {
            cartsDao.removeCartItem(cartSeq);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        // 返回刪除商品的結果
    }

    public boolean updateCartItemQuantity(int cartSeq, int quantity) {
        // 實現更新商品數量的邏輯，根據商品編號和數量更新購物車中相應的CartItem的數量
        //同新增，call cartsDao的方法操作資料庫
        try {
            cartsDao.updateCartItemQuantity(cartSeq, quantity);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
        // 返回更新商品數量的結果
    }

    /*public void setCartItemDao(CartsDao cartsDao) {
        this.cartsDao = cartsDao;
    }
    //屬性注入
    //ShoppingCart定義了一個setCartItemDao方法，通過這個方法可以在外部設置CartItemDao
     */
}