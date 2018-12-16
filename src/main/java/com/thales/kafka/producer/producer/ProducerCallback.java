package com.thales.kafka.producer.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProducerCallback implements Callback {

    private final Logger logger = LoggerFactory.getLogger(ProducerCallback.class);
    private static final int LIMIT_ERRORS = 5;

    private KafkaProducer<String, String> kafkaProducer;
    private ProducerRecord<String, String> data;
    private int errorsOccurred = 0;

    public ProducerCallback(ProducerRecord<String, String> data, KafkaProducer<String, String> kafkaProducer) {
        this.data = data;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
        if (exception != null) {
            if (errorsOccurred < LIMIT_ERRORS) {
                errorsOccurred++;
                kafkaProducer.send(data, this);
            } else {
                logger.error(data.toString(), exception);
            }
        }
    }
}
