package org.aura.plateforme_iot.controller;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.dto.AlertDto;
import org.aura.plateforme_iot.service.interfaceService.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    /**
     * Get all alerts (Accessible by USER and ADMIN)
     */
    @GetMapping("/user/alerts")
    public ResponseEntity<List<AlertDto>> getAllUserAlerts() {
        List<AlertDto> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    /**
     * Get all alerts (Admin-specific route)
     */
    @PostMapping("/admin/alerts/add")
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertDto alertDto) {
        AlertDto createdAlert = alertService.createAlert(alertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    @DeleteMapping("/admin/alerts/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable String id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}
