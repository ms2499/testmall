package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserOrders {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "orderNo")
    private int orderNo;
    @JsonProperty(value = "orderAccount")
    private String orderAccount;
    @JsonProperty(value = "orderDate")
    private String orderDate;
    @JsonProperty(value = "orderTotal")
    private int orderTotal;

    public int getOrderNo() {
        return orderNo;
    }
    public String getOrderAccount(){
        return orderAccount;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public int getOrderTotal(){
        return orderTotal;
    }

    public void setOrderAccount(String account){
        if (cstool.isEncoding(account, "ISO-8859-1"))
            this.orderAccount = cstool.iso2utf8(account);
        else
            this.orderAccount = account;
    }

    public void setOrderDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(date);
        LocalDateTime d = timestamp.toLocalDateTime();
        this.orderDate = formatter.format(d);
    }

    public UserOrders(int no, String account, String date, int total){
        orderNo = no;
        setOrderAccount(account);
        setOrderDate(date);
        orderTotal = total;
    }
}
