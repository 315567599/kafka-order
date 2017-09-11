package com.jiangchao.kafka.consumer;

/**
 * Created by Administrator on 2017/9/11.
 */

import com.jiangchao.dao.OrderExceptionRepository;
import com.jiangchao.dao.OrderRepository;
import com.jiangchao.model.Order;
import com.jiangchao.model.OrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.annotation.KafkaListeners;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private static final String SMALL_PRICE = "价格过小";
    private CountDownLatch latch = new CountDownLatch(1);
    @Autowired
    OrderRepository repository;

    @Autowired
    OrderExceptionRepository exceptionRepository;

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.json}")
    public void receive(Order order) {
        repository.insert(order);
        if(order.getPrice() < 10) {
            exceptionRepository.insert(new OrderException(order.getOrderID(), SMALL_PRICE));
        }
        LOGGER.info("received order ={}", order.toString());
        //latch.countDown();
    }
}
