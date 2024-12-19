package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.entity.Measure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasureMapper {

    MeasureDTO entityToDTO(Measure measure);
    Measure measureDTOToEntity(MeasureDTO measureDto);
}
