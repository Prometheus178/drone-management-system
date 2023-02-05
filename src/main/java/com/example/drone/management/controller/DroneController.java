package com.example.drone.management.controller;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.entity.Medication;
import com.example.drone.management.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Author: Sergey.
 */
@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        List<Drone> allDrones = droneService.getAllDrones();
        return new ResponseEntity<>(allDrones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Drone getDroneById(@PathVariable Long id) {
        return droneService.getDroneById(id);
    }

    @PostMapping
    public Drone createDrone(@RequestBody Drone drone) {
        return droneService.createDrone(drone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable Long id, @RequestBody Drone drone) {
        Drone existingDrone = droneService.getDroneById(id);
        if (existingDrone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Drone updatedDrone = droneService.updateDrone(id, drone);
        return new ResponseEntity<>(updatedDrone, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable Long id) {
        droneService.deleteDrone(id);
    }

    @PutMapping("/{id}/load/{medicationId}")
    public ResponseEntity<String> loadMedication(@PathVariable("id") Long id,
                                                 @PathVariable("medicationId") Long medicationId) {
        String result = droneService.loadMedication(id, medicationId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/loaded/medication")
    public ResponseEntity<Set<Medication>> getLoadedMedication(@PathVariable("id") Long id) {
        Set<Medication> result = droneService.getLoadedMedication(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/available/for/loading")
    public ResponseEntity<List<Drone>> findAvailableForLoading() {
        List<Drone> result = droneService.findAvailableForLoading();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/battery/level")
    public ResponseEntity<Integer> findDroneBatteryLevelById(@PathVariable("id") Long id) {
        Integer result = droneService.findDroneBatteryLevelById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}