package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.ZoneDto;
import org.aura.plateforme_iot.entity.Device;
import org.aura.plateforme_iot.entity.Zone;
import org.aura.plateforme_iot.repository.DeviceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ZoneMapper {
    public static final ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Mapping(source = "deviceIds", target = "devices")
    public abstract Zone toZone(ZoneDto zoneDto);

    public abstract ZoneDto toZoneDto(Zone zone);

    // Custom mapping: Convert List<String> deviceIds to List<Device>
    protected List<Device> mapDeviceIdsToDevices(List<String> deviceIds) {
        if (deviceIds == null) {
            return null;
        }
        return deviceIds.stream()
                .map(id -> deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found: " + id)))
                .toList();
    }
}
