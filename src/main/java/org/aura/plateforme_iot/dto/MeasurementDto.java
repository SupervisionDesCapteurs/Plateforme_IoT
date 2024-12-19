package org.aura.plateforme_iot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeasurementDto {
    private LocalDateTime timestamp;
    private Double value;
    private String deviceId;
}
