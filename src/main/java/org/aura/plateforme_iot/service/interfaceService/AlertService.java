package org.aura.plateforme_iot.service.interfaceService;

import org.aura.plateforme_iot.dto.AlertDto;

import java.util.List;

public interface AlertService {
    List<AlertDto> getAllAlerts();
    AlertDto createAlert(AlertDto alert);
    void deleteAlert(String id);
}
