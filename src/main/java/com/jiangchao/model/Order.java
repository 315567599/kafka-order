package com.jiangchao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String uid;
    private int price;
    private String orderID;
    private String mid;
    private String productName;

    public Order() {
        super();
    }

    public Order(String uid, int price, String orderID, String mid, String productName) {
       this.uid = uid;
       this.price = price;
       this.orderID = orderID;
       this.mid = mid;
       this.productName = productName;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
       this.uid = uid;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
       this.price = price;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
       this.mid = mid;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Order [uid=" + uid + ", orderID=" + orderID + ", productName=" + productName + "]";
    }
}
