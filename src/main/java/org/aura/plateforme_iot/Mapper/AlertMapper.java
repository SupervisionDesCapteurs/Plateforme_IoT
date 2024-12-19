package org.aura.plateforme_iot.Mapper;


import org.aura.plateforme_iot.dto.AlertDto;
import org.aura.plateforme_iot.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlertMapper {
    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);

    AlertDto toAlertDto(Alert alert);
    Alert toAlert(AlertDto alertDto);

}
