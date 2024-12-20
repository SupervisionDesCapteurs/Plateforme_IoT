package org.aura.plateforme_iot.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.service.interfaceService.MeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/measures")
@RequiredArgsConstructor
public class MeasureController {

    private final MeasureService measureService;

    @GetMapping
    public ResponseEntity<List<MeasureDTO>> getAllMeasures() {
        List<MeasureDTO> measures = measureService.getAllMeasures();
        return ResponseEntity.ok(measures);
    }

    @PostMapping
    public ResponseEntity<MeasureDTO> saveMeasure(@RequestBody MeasureDTO measure) {
        MeasureDTO savedMeasure = measureService.saveMeasure(measure);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMeasure);
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportMeasures() {
        String csvContent = measureService.exportMeasures();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=measures.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csvContent);
    }


    @GetMapping("/by-device/{deviceId}")
    public ResponseEntity<Page<MeasureDTO>> getMeasuresByDevice(
            @PathVariable String deviceId,
            Pageable pageable
    ) {
        Page<MeasureDTO> measures = measureService.getMeasuresByDevice(deviceId, pageable);
        return ResponseEntity.ok(measures);
    }
}
