package org.aura.plateforme_iot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("device")
@Data
public class Device {
    @Id
    private String id;
    private String name;

}
