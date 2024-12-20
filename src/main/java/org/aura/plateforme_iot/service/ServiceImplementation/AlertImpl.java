package org.aura.plateforme_iot.service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.AlertMapper;
import org.aura.plateforme_iot.dto.AlertDto;
import org.aura.plateforme_iot.entity.Alert;
import org.aura.plateforme_iot.entity.Enums.Severity;
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

        String message = generateAlertMessage(alertDto.getDeviceDto().getDeviceType().name(), alertDto.getSeverity());

        alertDto.setMessage(message);

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

    private String generateAlertMessage(String deviceType, Severity severity) {

        if ("temperature".equalsIgnoreCase(deviceType)) {
            return switch (severity) {
                case CRITICAL -> "Risque immédiat pour les équipements (Température > 40°C ou < -10°C)";
                case HIGH ->
                        "Situation préoccupante nécessitant une action rapide (Température 35-40°C ou -5°C à -10°C)";
                case MEDIUM -> "Situation à surveiller (Température 30-35°C ou 0°C à -5°C)";
                case LOW -> "Légère déviation des valeurs optimales (Température 25-30°C)";
                case NORMAL -> "Température dans la plage optimale (20-25°C)";
                default -> "Alerte Température inconnue";
            };
        } else if ("humidity".equalsIgnoreCase(deviceType)) {
            return switch (severity) {
                case CRITICAL -> "Risque de dommages matériels (Humidité > 90% ou < 20%)";
                case HIGH -> "Conditions défavorables (Humidité 80-90% ou 20-30%)";
                case MEDIUM -> "Situation à surveiller (Humidité 70-80% ou 30-40%)";
                case LOW -> "Légère déviation (Humidité 65-70% ou 40-45%)";
                case NORMAL -> "Humidité dans la plage optimale (45-65%)";
                default -> "Alerte Humidité inconnue";
            };
        }

        return "Alerte inconnue";
    }
}
