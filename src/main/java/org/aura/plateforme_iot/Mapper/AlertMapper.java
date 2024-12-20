package org.aura.plateforme_iot.Mapper;


import org.aura.plateforme_iot.dto.AlertDto;
import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.dto.DeviceResponseDto;
import org.aura.plateforme_iot.entity.Alert;
import org.aura.plateforme_iot.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlertMapper {
    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);

    @Mapping(source = "device.id", target = "deviceId")
    @Mapping(source = "device", target = "deviceDto")
    AlertDto toAlertDto(Alert alert);

    @Mapping(source = "deviceId", target = "device.id")
    @Mapping(source = "deviceDto", target = "device")
    Alert toAlert(AlertDto alertDto);

    default DeviceResponseDto mapDeviceToDeviceDto(Device device) {
        if (device == null) {
            return null;
        }
        return DeviceResponseDto.builder()
                .id(device.getId())
                .deviceType(device.getDeviceType())
                .name(device.getName())
                .build();
    }

}
