package com.jiangchao.kafka.consumer;

/**
 * Created by Administrator on 2017/9/11.
 */

import com.jiangchao.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.annotation.KafkaListeners;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.json}")
    public void receive(Order order) {
        LOGGER.info("received order ={}", order.toString());
        //latch.countDown();
    }
}
