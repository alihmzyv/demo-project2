package com.example.demoproject2.model.dto.status.resp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StatusCountRespDto {
    Short statusId;
    Integer count;
}
