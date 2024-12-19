package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.ZoneDto;
import org.aura.plateforme_iot.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    ZoneDto toZoneDto(Zone zone);
    Zone toZone(ZoneDto zoneDto);
}
