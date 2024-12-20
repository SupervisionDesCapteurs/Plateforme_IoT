package org.aura.plateforme_iot.service.interfaceService;

import org.aura.plateforme_iot.dto.ZoneDto;

import java.util.List;

public interface ZoneService {
    List<ZoneDto> getAllZones();
    ZoneDto getZoneById(String id);
    ZoneDto createZone(ZoneDto zoneDto);
    void deleteZone(String id);
}
