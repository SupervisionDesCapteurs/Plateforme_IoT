package org.aura.plateforme_iot.controller;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.dto.ZoneDto;
import org.aura.plateforme_iot.service.interfaceService.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/user/zones")
    public ResponseEntity<List<ZoneDto>> getAllZones() {
        List<ZoneDto> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @GetMapping("/user/zones/{id}")
    public ResponseEntity<ZoneDto> getZoneById(@PathVariable String id) {
        ZoneDto zone = zoneService.getZoneById(id);
        return ResponseEntity.ok(zone);
    }

    @PostMapping("/admin/zones/add")
    public ResponseEntity<ZoneDto> createZone(@Validated @RequestBody ZoneDto zoneDto) {
        ZoneDto createdZone = zoneService.createZone(zoneDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZone);
    }

    @DeleteMapping("/admin/zones/delete/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable String id) {
        zoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}
