package com.thales.kafka.producer.kafka;

import com.thales.kafka.producer.producer.ProducerCallback;
import com.thales.kafka.producer.util.JsonUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProducerReport {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Async("AsyncKafka")
    public void send(String topic, Object message) {

        try {
            ProducerRecord<String, String> data = new ProducerRecord<>(topic, JsonUtil.toJson(message));
            kafkaProducer.send(data, new ProducerCallback(data, kafkaProducer));
        } catch (Exception exception) {

            this.reportErrorMessage(topic, message, exception);
        }
    }

    private void reportErrorMessage(String topic, Object message, Exception exception) {

    }
}
