package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.entity.Measure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasureMapper {

    @Mapping(target = "deviceId", source = "device.id")
    @Mapping(target = "device", source = "device")
    MeasureDTO entityToDTO(Measure measure);

    @Mapping(source = "deviceId", target = "device.id")
    Measure measureDTOToEntity(MeasureDTO measureDto);
}

