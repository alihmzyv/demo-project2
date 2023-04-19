package com.example.demoproject2.model.dto.status.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusCountRespDto {
    Short statusId;
    Integer count;
}
