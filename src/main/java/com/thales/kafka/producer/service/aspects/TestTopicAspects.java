package com.thales.kafka.producer.service.aspects;

import com.thales.kafka.producer.kafka.ProducerReport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TestTopicAspects {

    private static final Logger logger = LoggerFactory.getLogger(TestTopicAspects.class);

    @Value("${kafka.report.topic.test}")
    private String topic;

    @Autowired
    private ProducerReport producerReport;

    @Before("execution (* com.thales.kafka.producer.entity.TestTopicMessages.sendMessageToTestTopic(..))")
    public void testTopicProducer(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            String msg = (String) args[0];

            /*SendMessageToTestRequest request = (SendMessageToTestRequest) args[0];
            String msg = request.getMsg();*/
            if (null != msg) {
                producerReport.send(topic, msg);
            }
        } catch (Exception ex) {

        }
    }

    /*@AfterReturning(pointcut = "execution(* com.thales.kafka.producer.entity.TestTopicMessages.sendMessageToTestTopic(..)) ",
            returning = "response")
    public void testTopicListener(Object response) {
        try {
            String msg = (String) response;

            if (null != msg) {
                producerReport.send(topic, msg);
            }
        } catch (Exception ex) {
            logger.error("Error posting test message in topic", ex);
            throw ex;
        }
    }*/
}