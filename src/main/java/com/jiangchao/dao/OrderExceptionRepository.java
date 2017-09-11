package com.jiangchao.dao;

import com.jiangchao.model.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/11.
 */

@Component
public class OrderExceptionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insert(OrderException exception) {
       int row = jdbcTemplate.update(
               "INSERT INTO t_order_exception(orderID, exception, created_ts) VALUES (?,?,?)",
               exception.getOrderID(),
               exception.getException(),
               (int)(System.currentTimeMillis()/1000)
       );

       return row > 0 ? true :false;
    }
}
