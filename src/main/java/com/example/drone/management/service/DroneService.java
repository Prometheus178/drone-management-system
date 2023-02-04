package com.example.drone.management.service;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Sergey.
 */
@Service
public class DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Drone getDroneById(Long id) {
        return droneRepository.findById(id).orElse(null);
    }

    public Drone createDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public Drone updateDrone(Long id, Drone drone) {
        Drone existingDrone = droneRepository.findById(id).orElse(null);
        existingDrone.setSerialNumber(drone.getSerialNumber());
        existingDrone.setModel(drone.getModel());
        existingDrone.setWeightLimit(drone.getWeightLimit());
        existingDrone.setBatteryCapacity(drone.getBatteryCapacity());
        existingDrone.setState(drone.getState());
        return droneRepository.save(existingDrone);
    }

    public void deleteDrone(Long id) {
        droneRepository.deleteById(id);
    }
}
