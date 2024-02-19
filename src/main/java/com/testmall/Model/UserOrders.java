package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class UserOrders {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "orderNo")
    private int orderNo;
    @JsonProperty(value = "orderAccount")
    private String orderAccount;
    @JsonProperty(value = "orderDate")
    private Timestamp orderDate;
    @JsonProperty(value = "orderTotal")
    private int orderTotal;

    public int getOrderNo() {
        return orderNo;
    }
    public String getOrderAccount(){
        return orderAccount;
    }
    public Timestamp getOrderDate() {
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

    public void setOrderDate(Timestamp date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.orderDate = Timestamp.valueOf(date.toLocalDateTime().format(formatter));
    }

    public UserOrders(int no, String account, Timestamp date, int total){
        orderNo = no;
        setOrderAccount(account);
        setOrderDate(date);
        orderTotal = total;
    }
}
