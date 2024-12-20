package org.aura.plateforme_iot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeasureDTO {
    private String id;
    private LocalDateTime timestamp;
    private Double value;
    private String deviceId;
    private DeviceDto device;
}
