package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device,String> {
}
