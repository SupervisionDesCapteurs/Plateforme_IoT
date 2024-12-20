package org.aura.plateforme_iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.aura.plateforme_iot.repository")
public class PlateformeIoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlateformeIoTApplication.class, args);
    }

}
