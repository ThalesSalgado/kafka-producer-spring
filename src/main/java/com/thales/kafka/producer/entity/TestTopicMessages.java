package com.thales.kafka.producer.entity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestTopicMessages {

    private static final List<String> messagesList = new ArrayList<>();

    public void sendMessageToTestTopic(String msg) {
        try {
            messagesList.add(msg);
        } catch(Exception ex) {

        }
    }
}
