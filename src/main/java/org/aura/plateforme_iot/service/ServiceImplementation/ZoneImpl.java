package org.aura.plateforme_iot.service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.ZoneMapper;
import org.aura.plateforme_iot.dto.ZoneDto;
import org.aura.plateforme_iot.entity.Zone;
import org.aura.plateforme_iot.repository.ZoneRepository;
import org.aura.plateforme_iot.service.interfaceService.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public List<ZoneDto> getAllZones() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream()
                .map(zoneMapper::toZoneDto)
                .toList();
    }

    @Override
    public ZoneDto getZoneById(String id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        return zoneMapper.toZoneDto(zone);
    }

    @Override
    public ZoneDto createZone(ZoneDto zoneDto) {
        Zone zone = zoneMapper.toZone(zoneDto);
        Zone savedZone = zoneRepository.save(zone);
        return zoneMapper.toZoneDto(savedZone);
    }

    @Override
    public void deleteZone(String id) {
        if (!zoneRepository.existsById(id)) {
            throw new IllegalArgumentException("Zone not found");
        }
        zoneRepository.deleteById(id);
    }
}
