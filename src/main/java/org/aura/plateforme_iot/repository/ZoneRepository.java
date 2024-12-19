package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {
}
