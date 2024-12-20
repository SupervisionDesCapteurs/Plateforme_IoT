package org.aura.plateforme_iot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "measure")
@Data
public class Measure {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private Double value;
    @DBRef
    private Device device;
}
