package com.testmall.Model;

import com.testmall.Tools.CharsetTool;

public class Commodities {
    CharsetTool cstool = new CharsetTool();
    private Long commodityID;
    private String commodityName;
    private int commodityQty;
    private Long commodityPrice;
    private String commodityTag;
    private String commodityImgPath;
    private String commodityDetail;
    private byte commoditySaleFlag;
    private byte commodityDiscount;
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
        if (cstool.isEncoding(tag, "ISO-8859-1"))
            this.commodityTag = cstool.iso2utf8(tag);
        else
            this.commodityTag = tag;
    }

    public String getCommodityImgPath(){
        return commodityImgPath;
    }

    public void setCommodityImgPath(String path) {
        if (cstool.isEncoding(path, "ISO-8859-1"))
            this.commodityImgPath = cstool.iso2utf8(path);
        else
            this.commodityImgPath = path;
    }

    public String getCommodityDetail() {
        return commodityDetail;
    }

    public void setCommodityDetail(String detail) {
        if (cstool.isEncoding(detail, "ISO-8859-1"))
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
