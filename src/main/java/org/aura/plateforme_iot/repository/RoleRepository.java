package org.aura.plateforme_iot.repository;

import org.aura.plateforme_iot.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
