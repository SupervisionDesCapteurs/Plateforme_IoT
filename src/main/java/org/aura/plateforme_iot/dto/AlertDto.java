package org.aura.plateforme_iot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.aura.plateforme_iot.entity.Enums.Severity;

import java.time.LocalDateTime;

@Data
@Builder
public class AlertDto {

    private int id;

    @NotNull(message = "La sévérité de l'alerte est requise.")
    private Severity severity;

    @NotBlank(message = "Le message de l'alerte ne peut pas être vide.")
    private String message;

    @NotNull(message = "Le timestamp de l'alerte est requis.")
    private LocalDateTime dateTime;

}
