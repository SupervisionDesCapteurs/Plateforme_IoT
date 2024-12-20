package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface AlertRepository extends MongoRepository<Alert, String> {
}
