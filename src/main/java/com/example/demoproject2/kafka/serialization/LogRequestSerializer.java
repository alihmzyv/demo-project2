package com.example.demoproject2.kafka.serialization;

import com.example.demoproject2.model.dto.log.CreateLogRequestKafkaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class LogRequestSerializer implements Serializer<CreateLogRequestKafkaDto> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public byte[] serialize(String topic, CreateLogRequestKafkaDto data) {
        return objectMapper.writeValueAsBytes(data);
    }
}
