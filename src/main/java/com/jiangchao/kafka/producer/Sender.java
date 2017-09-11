package com.jiangchao.kafka.producer;

/**
 * Created by Administrator on 2017/9/11.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import  com.jiangchao.model.Car;

public class Sender {
    private static final  Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Car> kafkaTemplate;

    public void send(Car car) {
       LOGGER.info("sending car='{}'", car.toString());
       //kafkaTemplate.send(jsonTopic,"test", car);
        kafkaTemplate.send(jsonTopic, car);
    }
}
