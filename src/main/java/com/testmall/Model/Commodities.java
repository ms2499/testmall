package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "商品實體類")
public class Commodities implements Serializable {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "commodityID")
    @Schema(description = "商品ID")
    private Long commodityID;
    @JsonProperty(value = "commodityName")
    @Schema(description = "商品名稱")
    private String commodityName;
    @JsonProperty(value = "commodityQty")
    @Schema(description = "商品數量")
    private int commodityQty;
    @JsonProperty(value = "commodityPrice")
    @Schema(description = "商品價格")
    private Long commodityPrice;
    @JsonProperty(value = "commodityTag")
    @Schema(description = "商品種類")
    private String commodityTag;
    @JsonProperty(value = "commodityImgPath")
    @Schema(description = "商品圖片路徑")
    private String commodityImgPath;
    @JsonProperty(value = "commodityDetail")
    @Schema(description = "商品詳細資料")
    private String commodityDetail;
    @JsonProperty(value = "commoditySaleFlag")
    @Schema(description = "是否販賣中")
    private byte commoditySaleFlag;
    @JsonProperty(value = "commodityDiscount")
    @Schema(description = "是否有折扣")
    private byte commodityDiscount;
    @JsonProperty(value = "commodityDisRate")
    @Schema(description = "打幾折")
    private byte commodityDisRate;

    public Long getCommodityID(){
        return commodityID;
    }

    public void setCommodityID(Long id){
        this.commodityID = id;
    }

    public String getCommodityName(){
        return commodityName;
    }

    public void setCommodityName(String name){
        if (cstool.isEncoding(name, "ISO-8859-1"))
            this.commodityName = cstool.iso2utf8(name);
        else
            this.commodityName = name;
    }

    public int getCommodityQty(){
        return commodityQty;
    }

    public void setCommodityQty(int qty){
        this.commodityQty = qty;
    }

    public Long getCommodityPrice(){
        return commodityPrice;
    }

    public void setCommodityPrice(Long price){
        this.commodityPrice = price;
    }

    public String getCommodityTag(){
        return commodityTag;
    }

    public void setCommodityTag(String tag){
        if (tag != null && cstool.isEncoding(tag, "ISO-8859-1"))
            this.commodityTag = cstool.iso2utf8(tag);
        else
            this.commodityTag = tag;
    }

    public String getCommodityImgPath(){
        return commodityImgPath;
    }

    public void setCommodityImgPath(String path) {
        if (path != null && cstool.isEncoding(path, "ISO-8859-1"))
            this.commodityImgPath = cstool.iso2utf8(path);
        else
            this.commodityImgPath = path;
    }

    public String getCommodityDetail() {
        return commodityDetail;
    }

    public void setCommodityDetail(String detail) {
        if (detail != null && cstool.isEncoding(detail, "ISO-8859-1"))
            this.commodityDetail = cstool.iso2utf8(detail);
        else
            this.commodityDetail = detail;
    }

    public byte getCommoditySaleFlag() {
        return commoditySaleFlag;
    }

    public void setCommoditySaleFlag(byte saleFlag) {
        this.commoditySaleFlag = saleFlag;
    }

    public byte getCommodityDiscount() {
        return commodityDiscount;
    }

    public void setCommodityDiscount(byte discount) {
        this.commodityDiscount = discount;
    }

    public byte getCommodityDisRate() {
        return commodityDisRate;
    }

    public void setCommodityDisRate(byte disRate) {
        this.commodityDisRate = disRate;
    }

    public Commodities(){
        commodityID = 0L;
        commodityName = "";
        commodityQty = 0;
        commodityPrice = 0L;
        commodityTag = "";
        commodityImgPath = "";
        commodityDetail = "";
        commoditySaleFlag = 1;
        commodityDiscount = 0;
        commodityDisRate = 0;
    }

    public Commodities(Long id, String name, int qty, Long price, String tag, String img_path, String detail, byte slFlag, byte dcount, byte drate){
        commodityID = id;
        setCommodityName(name);
        commodityQty = qty;
        commodityPrice = price;
        setCommodityTag(tag);
        setCommodityImgPath(img_path);
        setCommodityDetail(detail);
        commoditySaleFlag = slFlag;
        commodityDiscount = dcount;
        commodityDisRate = drate;
    }
}
