package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MeasureMapper.class})
public interface DeviceMapper {

    @Mapping(source = "measureList", target = "measureList")
    DeviceDto entityToDto(Device device);

    @Mapping(source = "measureList", target = "measureList")
    Device dtoToEntity(DeviceDto deviceDto);
}