package org.aura.plateforme_iot.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.DeviceStatus;
import org.aura.plateforme_iot.entity.Enums.DeviceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "device")
@Data
public class Device {
    @Id
    private String id;
    private String name;
    private DeviceType deviceType;
    private DeviceStatus deviceStatus;
    private LocalDateTime lastCommunication;
    @DBRef
    private List<Measure> measureList;


}
