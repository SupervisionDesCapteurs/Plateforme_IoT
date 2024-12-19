package org.aura.plateforme_iot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.DeviceStatus;
import org.aura.plateforme_iot.entity.Enums.DeviceType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DeviceDto {
    @NotBlank
    private String name;
    private DeviceType deviceType;
    private DeviceStatus deviceStatus;
    private LocalDateTime lastCommunication;
    private List<MeasurementDto> measureList;

}
