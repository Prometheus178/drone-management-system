package com.example.drone.management.sheduler;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.service.DroneService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Sergey.
 */
@Log
@Component
public class CronScheduler {

    private final DroneService droneService;

    @Autowired
    public CronScheduler(DroneService droneService) {
        this.droneService = droneService;
    }

    /**
     * seconds minutes hours day-of-month month day-of-week
     *    0       0      8        *         *        ?
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void checkBatteryLevelOfDrones() {// TODO: 05.02.2023 make it changeable in runtime
        List<Drone> allDrones = droneService.getAllDrones();
        if (allDrones != null && !allDrones.isEmpty()){
            allDrones.forEach(
                drone -> {
                    long timestamp = System.currentTimeMillis();
                    log.info("Battery level of drone: id=" +drone.getId() + " checked at: " + timestamp
                            + " with level: " + drone.getBatteryCapacity());
                }
            );
        }
    }
}
