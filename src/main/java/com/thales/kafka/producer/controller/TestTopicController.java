package com.thales.kafka.producer.controller;

import com.thales.kafka.producer.dtos.requests.SendMessageToTestRequest;
import com.thales.kafka.producer.entity.TestTopicMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestTopicController {

    @Autowired
    private TestTopicMessages testTopicMessages;

    @PostMapping(value = "/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageToTestRequest request){
        testTopicMessages.sendMessageToTestTopic(request.getMsg());

        return new ResponseEntity<>("Msg enviada", HttpStatus.OK);
    }
}
