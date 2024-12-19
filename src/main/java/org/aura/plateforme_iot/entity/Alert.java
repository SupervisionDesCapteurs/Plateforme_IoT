package org.aura.plateforme_iot.entity;

import lombok.Data;
import org.aura.plateforme_iot.entity.enums.Severity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collation = "alerts")
@Data
public class Alert {

    @Id
    private String id;

    private Severity severity;
    private String message;
    private LocalDateTime dateTime;
}
