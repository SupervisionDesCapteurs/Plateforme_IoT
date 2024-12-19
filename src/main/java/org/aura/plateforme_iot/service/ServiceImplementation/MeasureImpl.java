package org.aura.plateforme_iot.service.ServiceImplementation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aura.plateforme_iot.Mapper.MeasureMapper;
import org.aura.plateforme_iot.dto.MeasureDTO;
import org.aura.plateforme_iot.entity.Measure;
import org.aura.plateforme_iot.repository.MeasureRepository;
import org.aura.plateforme_iot.service.interfaceService.MeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MeasureImpl implements MeasureService {
    private final MeasureRepository measureRepository;
    private final MeasureMapper measureMapper;

    @Override
    public List<MeasureDTO> getAllMeasures() {
        List<Measure> measures = measureRepository.findAll();
        return  measures.stream().map(measureMapper::entityToDTO).toList();
    }

    @Override
    public MeasureDTO saveMeasure(MeasureDTO measure) {
        Measure saveMeasure = measureMapper.measureDTOToEntity(measure);
        return measureMapper.entityToDTO(measureRepository.save(saveMeasure));
    }

    @Override
    public void exportMeasures(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("content-disposition", "attachment; filename=measures.csv");
            List<Measure> measures = measureRepository.findAll();
            PrintWriter writer = response.getWriter();
            writer.println("ID,DeviceID,Value,Timestamp");
            for(Measure measure : measures) {
                writer.println(
                        measure.getId() + "," +
                                measure.getDevice().getName() + "," +
                                measure.getValue() + "," +
                                measure.getTimestamp()
                );
            }
            writer.flush();

        }catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'exportation des mesures en CSV", e);
        }
    }

    @Override
    public Page<MeasureDTO> getMeasuresByDevice(String deviceId, Pageable pageable) {
        Page<Measure> measures = measureRepository.findByDeviceId(deviceId,pageable);
        return measures.map(measureMapper::entityToDTO);
    }
}
