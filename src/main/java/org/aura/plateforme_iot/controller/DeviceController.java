package org.aura.plateforme_iot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.service.interfaceService.DeviceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class DeviceController {

    private final DeviceService deviceService;
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/user/devices")
    public ResponseEntity<Page<DeviceDto>> getAllDevices(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
     Page<DeviceDto> deviceList = deviceService.listAllDevices(pageable);
     return ResponseEntity.ok(deviceList);
    }

    @GetMapping("/user/devices/by-zone/{zoneId}")
    public ResponseEntity<Page<DeviceDto>> getAllDevicesByZone(@PathVariable String zoneId,@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<DeviceDto> deviceListe = deviceService.listDevicesByZone(zoneId,pageable);
        return ResponseEntity.ok(deviceListe);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/admin/devices")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody @Valid DeviceDto deviceDto) {
        DeviceDto deviceSaved = deviceService.addDevice(deviceDto);
        return ResponseEntity.status(201).body(deviceSaved);
    }


}
