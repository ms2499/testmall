package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;
public class Carts {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "CartSeq")
    private int cartSeq;    //購物車流水號
    @JsonProperty(value = "cartAccount")
    private String cartAccount;    //使用者帳號
    @JsonProperty(value = "cartCommodityID")
    private Long cartCommodityID;    //商品編號
    @JsonProperty(value = "cartQty")
    private int cartQty;    //購買數量

    public Carts(int cartSeq, String cartAccount, long cartCommodityID, int cartQty) {
        if (cartQty <= 0){
            throw new IllegalArgumentException("這裡沒東西 cartQty must be greater than 0");
        }
        // 基本參數合法性檢查

        // 以下把傳入參數塞給物件屬性
        this.cartSeq = cartSeq;
        setCartAccount(cartAccount);
        //cartAccount需做轉碼處理,所以不直接賦值
        this.cartCommodityID = cartCommodityID;
        this.cartQty = cartQty;
        // 確保已被正確初始化
    }
    // 2024-01-22新增 要多一個無參數建構子,給屬性預設值,不然在接收前台資料轉為物件時會壞掉
    public Carts(){
        this.cartSeq = 0;
        this.cartAccount = "";
        this.cartCommodityID = 0L;
        this.cartQty = 0;
    }
    // Getters and setters
    public int getCartSeq() {
        return cartSeq;
    }
    public void setCartSeq(int cartSeq) {
        this.cartSeq = cartSeq;
    }

    public String getCartAccount() {
        return cartAccount;
    }
    public void setCartAccount(String cartAccount) {
        // 2024-01-22新增 字串類型資料需判斷編碼
        if (cstool.isEncoding(cartAccount, "ISO-8859-1"))
            this.cartAccount= cstool.iso2utf8(cartAccount);
        else
            this.cartAccount = cartAccount;
    }

    public Long getCartCommodityID() {
        return cartCommodityID;
    }

    public void setCartCommodityID(Long cartCommodityID) {
        this.cartCommodityID = cartCommodityID;
    }

    public int getCartQty() {
        return cartQty;
    }
    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    // 其他可能需要的方法
}
