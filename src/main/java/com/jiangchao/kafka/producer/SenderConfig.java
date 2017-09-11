package com.jiangchao.kafka.producer;

/**
 * Created by Administrator on 2017/9/11.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.jiangchao.model.Car;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class SenderConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Car> producerFactory() {
        return new DefaultKafkaProducerFactory<String, Car>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Car> kafkaTemplate() {
       return new KafkaTemplate<String, Car>(producerFactory());
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }


}
