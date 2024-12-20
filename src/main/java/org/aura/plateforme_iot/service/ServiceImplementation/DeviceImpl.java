package org.aura.plateforme_iot.service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.DeviceMapper;
import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.entity.Device;
import org.aura.plateforme_iot.repository.DeviceRepository;
import org.aura.plateforme_iot.service.interfaceService.DeviceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public Page<DeviceDto> listAllDevices(Pageable pageable) {
        return deviceRepository.findAll(pageable).map(deviceMapper::entityToDto);
    }

    @Override
    public DeviceDto addDevice(DeviceDto deviceDTO) {
        Device device = deviceMapper.dtoToEntity(deviceDTO);
        return deviceMapper.entityToDto( deviceRepository.save(device));
    }
}
