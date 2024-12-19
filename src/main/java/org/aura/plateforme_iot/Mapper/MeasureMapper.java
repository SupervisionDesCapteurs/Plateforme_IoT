package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.MeasurementDto;
import org.aura.plateforme_iot.entity.Measure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasureMapper {

    MeasurementDto entityToDTO(Measure measure);
    Measure measureDTOToEntity(MeasurementDto measureDto);
}
