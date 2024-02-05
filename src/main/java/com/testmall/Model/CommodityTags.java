package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;

//↓定義一個CommodityTags類別
public class CommodityTags { /*商品種類表*/
    //↓前端的屬性都是Json,這裡讓轉碼工具不會被Json影響
    @JsonIgnore
    CharsetTool cstool= new CharsetTool();
    //↓Json屬性:會找到前端定義好的屬性，private那行定錯也沒關係
    @JsonProperty(value = "commoditySubTag")
    private String commoditySubTag; /*小類*/
    @JsonProperty(value = "commodityMainTag")
    private String commodityMainTag; /*大類*/
//↓定義此類別建構子
    public CommodityTags(String commoditySubTag,String commodityMainTag) {
        //↓原本這裡是要在建構子的{ }區塊中定義建構子要做些什麼事
        // 把傳入參數塞給物件屬性,but小類大類需做轉碼處理,所以不直接賦值
        setCommoditySubTag(commoditySubTag);
        setCommodityMainTag(commodityMainTag);
    }
//↓(無參數)建構子：給屬性預設值
    public CommodityTags(){
        this.commoditySubTag = "";
        this.commodityMainTag = "";
    }
/*↓Getters and setters 控制著對一個物件（例如類中的變數）的進入和修改
Getter 是用來取得內部資料，而 Setter 是用來修改內部資料。
這樣的做法讓我們能夠有更多控制權，確保資料的正確性和安全性。*/
    public String getCommoditySubTag(){
        return commoditySubTag;
    }

    public void setCommoditySubTag(String tag){
        /*↓(因為Tandem才加的,只有Tandem用ISO)判斷從資料庫拿出來的字串類型資料是否ISO編碼。
        我們平常用的是UTF-8,放進資料庫需轉成ISO;出資料庫反之亦然。大部分的情況會讀到ISO。
        tag參數=空的框框,未來有參數就會直接帶入參數ex.123->123,所以框框亂訂沒關係(x)*/
        if (cstool.isEncoding(commoditySubTag, "ISO-8859-1"))
            this.commoditySubTag= cstool.iso2utf8(tag);
        else
            this.commoditySubTag = tag;
    }
    public String getCommodityMainTag(){
        return commodityMainTag;
    }

    public void setCommodityMainTag(String tag){

        if (cstool.isEncoding(commodityMainTag, "ISO-8859-1"))
            this.commodityMainTag= cstool.iso2utf8(tag);
        else
            this.commodityMainTag = tag;
    }

}
