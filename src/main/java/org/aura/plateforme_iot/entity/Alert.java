package org.aura.plateforme_iot.entity;

import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.Severity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "alerts")
@Data
public class Alert {

    @Id
    private String id;

    private Severity severity;
    private String message;
    private LocalDateTime dateTime;

    @DocumentReference
    private Device device;
}
