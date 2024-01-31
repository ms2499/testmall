package com.testmall.Model;

public class OrderLists {
    private int orderSeq;
    private int orderNo;
    private long orderCommodityID;
    private int orderQty;
    private long orderPrice;
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
