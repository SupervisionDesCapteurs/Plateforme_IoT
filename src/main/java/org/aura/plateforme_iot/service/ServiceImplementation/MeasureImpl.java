package org.aura.plateforme_iot.service.ServiceImplementation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.MeasureMapper;
import org.aura.plateforme_iot.dto.DeviceDto;
import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.entity.Device;
import org.aura.plateforme_iot.entity.Measure;
import org.aura.plateforme_iot.repository.DeviceRepository;
import org.aura.plateforme_iot.repository.MeasureRepository;
import org.aura.plateforme_iot.service.interfaceService.MeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MeasureImpl implements MeasureService {
    private final MeasureRepository measureRepository;
    private final MeasureMapper measureMapper;
    private final DeviceRepository deviceRepository;

    @Override
    public List<MeasureDTO> getAllMeasures() {
        List<Measure> measures = measureRepository.findAll();
        return  measures.stream().map(measureMapper::entityToDTO).toList();
    }

    @Override
    public MeasureDTO saveMeasure(MeasureDTO measureDTO) {
        if (measureDTO.getDeviceId() == null || measureDTO.getDeviceId().isEmpty()) {
            throw new IllegalArgumentException("Device ID cannot be null or empty");
        }

        Device device = deviceRepository.findById(measureDTO.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + measureDTO.getDeviceId()));

        Measure measure = measureMapper.measureDTOToEntity(measureDTO);
        measure.setDevice(device);

        Measure savedMeasure = measureRepository.save(measure);

        MeasureDTO savedMeasureDTO = measureMapper.entityToDTO(savedMeasure);

        DeviceDto deviceDto = DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .lastCommunication(device.getLastCommunication())
                .deviceStatus(device.getDeviceStatus())
                .deviceType(device.getDeviceType())
                .build();
        savedMeasureDTO.setDevice(deviceDto);

        return savedMeasureDTO;
    }

    @Override
    public String exportMeasures() {
        List<Measure> measures = measureRepository.findAll();
        StringBuilder csvContent = new StringBuilder("ID,DeviceName,Value,Timestamp\n");

        for (Measure measure : measures) {
            String deviceName = (measure.getDevice() != null) ? measure.getDevice().getName() : "Unknown";
            String timestamp = (measure.getTimestamp() != null) ? measure.getTimestamp().toString() : "N/A";
            csvContent.append(measure.getId()).append(",")
                    .append(deviceName).append(",")
                    .append(measure.getValue()).append(",")
                    .append(timestamp).append("\n");
        }

        return csvContent.toString();
    }

    @Override
    public Page<MeasureDTO> getMeasuresByDevice(String deviceId, Pageable pageable) {
        Page<Measure> measures = measureRepository.findByDeviceId(deviceId,pageable);
        return measures.map(measureMapper::entityToDTO);
    }
}
