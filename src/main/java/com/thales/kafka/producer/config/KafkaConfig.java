package com.thales.kafka.producer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String kafkaServers;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public KafkaProducer<String, String> ofKafkaProducer() {

        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer<>(props);
    }
}
