package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Measure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasureRepository extends MongoRepository<Measure,String> {
    Page<Measure> findByDeviceId(String deviceId, Pageable pageable);
}
