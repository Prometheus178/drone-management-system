package com.example.drone.management.controller;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Drone updatedDrone = droneService.updateDrone(id, existingDrone);
        return new ResponseEntity<>(updatedDrone, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable Long id) {
        droneService.deleteDrone(id);
    }
}