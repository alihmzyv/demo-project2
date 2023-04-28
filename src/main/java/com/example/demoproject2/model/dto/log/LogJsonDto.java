package com.example.demoproject2.model.dto.log;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LogJsonDto {
    Object[] args;
    Object result;
}
