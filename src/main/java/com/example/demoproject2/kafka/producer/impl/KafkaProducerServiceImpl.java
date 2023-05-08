package com.example.demoproject2.kafka.producer.impl;

import com.example.demoproject2.kafka.config.KafkaTopicConfig;
import com.example.demoproject2.kafka.producer.KafkaProducerService;
import com.example.demoproject2.model.dto.log.CreateLogRequestKafkaDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    KafkaTemplate<String, CreateLogRequestKafkaDto> kafkaTemplate;
    KafkaTopicConfig kafkaTopicConfig;

    @Override
    public void createLog(CreateLogRequestKafkaDto createLogRequest) {
        kafkaTemplate.send(kafkaTopicConfig.getTopicName(), createLogRequest);
    }
}
