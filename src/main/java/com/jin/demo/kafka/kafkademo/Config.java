package com.jin.demo.kafka.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class Config {

    @KafkaListener(topicPattern = "log_topic")
    public void log(ConsumerRecord<Integer, String> record){
        System.out.println(record.value());
    }
}
