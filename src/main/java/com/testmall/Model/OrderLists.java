package com.testmall.Model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "訂單詳細資料實體類")
public class OrderLists {
    @Schema(description = "訂單流水號")
    private int orderSeq;
    @Schema(description = "訂單編號")
    private int orderNo;
    @Schema(description = "商品ID")
    private long orderCommodityID;
    @Schema(description = "購買數量")
    private int orderQty;
    @Schema(description = "總金額")
    private long orderPrice;
    @Schema(description = "是否退貨註記")
    private int orderReturn;

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setOrderCommodityID(long orderCommodityID) {
        this.orderCommodityID = orderCommodityID;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderReturn(int orderReturn) {
        this.orderReturn = orderReturn;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public long getOrderCommodityID() {
        return orderCommodityID;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public long getOrderPrice() {
        return orderPrice;
    }

    public int getOrderReturn() {
        return orderReturn;
    }

    public OrderLists(int seq, int no, long id, int qty, long price, int ret){
        orderSeq = seq;
        orderNo = no;
        orderCommodityID = id;
        orderQty = qty;
        orderPrice = price;
        orderReturn = ret;
    }
}
