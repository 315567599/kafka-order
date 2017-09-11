package com.jiangchao.model;

/**
 * Created by Administrator on 2017/9/11.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class OrderException {
    private String orderID;
    private String exception;
    public OrderException() {
        super();
    }
    public OrderException(String orderID, String exception) {
       this.orderID = orderID;
       this.exception = exception;
    }

    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
       this.orderID = orderID;
    }

    public String getException() {
        return exception;
    }
    public void setException(String exception) {
       this.exception = exception;
    }

    @Override
    public String toString() {
        return "OrderException [orderID=" + orderID + ", exception=" + exception + "]";
    }
}
