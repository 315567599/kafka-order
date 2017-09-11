package com.jiangchao.kafka.consumer;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.jiangchao.model.Car;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */


@Configuration
@EnableKafka
public class ReceiverConfig {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
       Map<String, Object> props = new HashMap<>();
       props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
       props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
       props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

       return props;
    }

    @Bean
    public ConsumerFactory<String, Car> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, Car>(
                consumerConfigs(),
                new org.apache.kafka.common.serialization.StringDeserializer(),
                new org.springframework.kafka.support.serializer.JsonDeserializer<>(Car.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Car> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Car> factory = new ConcurrentKafkaListenerContainerFactory<>( );
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}
