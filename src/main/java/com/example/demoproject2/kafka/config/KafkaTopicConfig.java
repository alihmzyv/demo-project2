package com.example.demoproject2.kafka.config;

import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Getter
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name(topicName)
                .build();
    }
}
