package org.aura.plateforme_iot.dto;

import lombok.Builder;
import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.DeviceType;

@Data
@Builder
public class DeviceResponseDto {
    private String id;
    private String name;
    private DeviceType deviceType;
}
