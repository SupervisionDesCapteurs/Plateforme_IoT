package org.aura.plateforme_iot.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.DeviceStatus;
import org.aura.plateforme_iot.entity.Enums.DeviceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("device")
@Data
public class Device {
    @Id
    private String id;
    @NotBlank
    private String name;
    private DeviceType deviceType;
    private DeviceStatus deviceStatus;
    private LocalDateTime lastCommunication;


}
