package com.jiangchao.dao;

import com.jiangchao.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Component
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insert(Order order) {
        int row = jdbcTemplate.update(
                "INSERT INTO t_order(orderID, uid, price, mid, productName, created_ts) VALUES(?,?,?,?,?,?)",
                order.getOrderID(),
                order.getUid(),
                order.getPrice(),
                order.getMid(),
                order.getProductName(),
                (int)(System.currentTimeMillis()/1000)
        );

        return row > 0 ? true : false;
    }

    public List<Order> getOrders() {
        String sql ="SELECT * FROM t_order";
        List<Order> results = jdbcTemplate.query(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = new Order(
                        resultSet.getString("uid"),
                        resultSet.getInt("price"),
                        resultSet.getString("orderID"),
                        resultSet.getString("mid"),
                        resultSet.getString("productName")
                );
                return order;
            }
        });

        return results;
    }
}
