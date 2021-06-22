package com.frontier.flyway;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class FlywayMigrator {

    @EventListener(ApplicationStartedEvent.class)
    public void migrate() {
        log.info("Migration-Start :::::");
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/spring_security", "root", "mysql");
        flyway.migrate();
        log.info("Migration-End :::::");
    }

}
