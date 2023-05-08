package com.example.demoproject2.model.dto.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateLogRequestKafkaDto {
    Long createdAt;
    Short operationService;
    Short operationType;
    String username;
    Long cashierCode;
    String json;
}
