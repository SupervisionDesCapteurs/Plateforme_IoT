package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface DeviceRepository extends MongoRepository<Device,String> {
    @Query("{'zone.$id': ?0}")
    Page<Device> findByZoneId(String zoneId, Pageable pageable);
}

