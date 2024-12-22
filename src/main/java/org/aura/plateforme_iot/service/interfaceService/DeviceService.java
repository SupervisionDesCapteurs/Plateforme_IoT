package org.aura.plateforme_iot.service.interfaceService;

import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {
    Page<DeviceDto> listAllDevices(Pageable pageable);
    DeviceDto addDevice(DeviceDto device);
    Page<DeviceDto> listDevicesByZone(String zoneId,Pageable pageable);


}
