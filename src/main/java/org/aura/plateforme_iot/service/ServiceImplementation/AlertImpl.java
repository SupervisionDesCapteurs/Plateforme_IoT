package org.aura.plateforme_iot.service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.AlertMapper;
import org.aura.plateforme_iot.dto.AlertDto;
import org.aura.plateforme_iot.entity.Alert;
import org.aura.plateforme_iot.repository.AlertRepository;
import org.aura.plateforme_iot.service.interfaceService.AlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;


    @Override
    public List<AlertDto> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();
        return alerts.stream()
                .map(alertMapper::toAlertDto)
                .toList();
    }

    @Override
    public AlertDto createAlert(AlertDto alertDto) {
        Alert alert = alertMapper.toAlert(alertDto);
        Alert savedAlert = alertRepository.save(alert);
        return alertMapper.toAlertDto(savedAlert);
    }

    @Override
    public void deleteAlert(String id) {
        if (!alertRepository.existsById(id)) {
            throw new IllegalArgumentException("Alert not found");
        }
        alertRepository.deleteById(id);
    }
}
