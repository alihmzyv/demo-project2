package com.example.demoproject2.kafka.producer;

import com.example.demoproject2.model.dto.log.CreateLogRequestKafkaDto;

public interface KafkaProducerService {
    void createLog(CreateLogRequestKafkaDto createLogRequest);
}
