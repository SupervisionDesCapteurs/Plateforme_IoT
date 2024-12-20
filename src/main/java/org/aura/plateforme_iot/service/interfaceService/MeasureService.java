package org.aura.plateforme_iot.service.interfaceService;

import jakarta.servlet.http.HttpServletResponse;
import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.entity.Measure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeasureService {
    List<MeasureDTO> getAllMeasures();
    MeasureDTO saveMeasure(MeasureDTO measureDTO);
    String exportMeasures();
    Page<MeasureDTO> getMeasuresByDevice(String deviceId, Pageable pageable);

}
