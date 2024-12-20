package org.aura.plateforme_iot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ZoneDto {

    private String id;
    @NotBlank(message = "Le nom de la zone ne peut pas être vide.")
    private String nom;

    @NotBlank(message = "Le type de la zone est requis.")
    private String type;

    @NotBlank(message = "La localisation de la zone est requise.")
    private String location;

    private List<DeviceDto> devices;
}
