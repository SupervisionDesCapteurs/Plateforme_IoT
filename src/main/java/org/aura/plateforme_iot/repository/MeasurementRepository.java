package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Measure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasurementRepository extends MongoRepository<Measure,String> {
}
