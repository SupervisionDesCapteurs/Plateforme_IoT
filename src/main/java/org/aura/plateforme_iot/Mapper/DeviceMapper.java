package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.entity.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDto entityToDto(Device device);
    Device dtoToEntity(DeviceDto deviceDto);
}
