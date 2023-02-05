package com.example.drone.management.service;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.entity.DroneState;
import com.example.drone.management.entity.Medication;
import com.example.drone.management.exception.InvalidFieldException;
import com.example.drone.management.exception.ObjectNotFoundException;
import com.example.drone.management.repository.DroneRepository;
import com.example.drone.management.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Sergey.
 */
@Service
public class DroneService {
    private final static int LOW_NEED_TO_CHARGE = 25;

    private final DroneRepository droneRepository;

    private final MedicationRepository medicationRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Drone getDroneById(Long id) {
        return droneRepository.findById(id).orElse(null);
    }

    public Drone createDrone(Drone drone) {
        double weightLimit = drone.getWeightLimit();
        // TODO: 05.02.2023 chain responsibility
        if (!isValidWeightLimit(weightLimit)) {
            throw new InvalidFieldException(); // todo more appropriate exception for client
        }
        if (!isValidPercentage(drone.getBatteryCapacity())) {
            throw new InvalidFieldException();
        }
        if (!isValidSerialNumLength(drone.getSerialNumber())) {
            throw new InvalidFieldException();
        }
        drone.setRemainingWeightCapacity(weightLimit);
        return droneRepository.save(drone);
    }

    public Drone updateDrone(Long id, Drone drone) {
        if (!isValidWeightLimit(drone.getWeightLimit())) {
            throw new InvalidFieldException();
        }
        if (!isValidPercentage(drone.getBatteryCapacity())) {
            throw new InvalidFieldException();
        }
        if (!isValidSerialNumLength(drone.getSerialNumber())) {
            throw new InvalidFieldException();
        }
        Drone existingDrone = droneRepository.findById(id).orElse(null);
        if (existingDrone == null){
            throw new ObjectNotFoundException();
        }
        existingDrone.setSerialNumber(drone.getSerialNumber());
        existingDrone.setModel(drone.getModel());
        existingDrone.setWeightLimit(drone.getWeightLimit());
        existingDrone.setBatteryCapacity(drone.getBatteryCapacity());
        existingDrone.setState(drone.getState());
        return droneRepository.save(existingDrone);
    }

    private boolean isValidSerialNumLength(String serialNumber) {
        return serialNumber.length() <= 100;
    }

    private boolean isValidPercentage(int batteryCapacity) {
        return batteryCapacity >= 0 && batteryCapacity <= 100;
    }

    public void deleteDrone(Long id) {
        droneRepository.deleteById(id);
    }

    public String loadMedication(Long droneId, Long medicationId) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        Medication medication = medicationRepository.findById(medicationId).orElse(null);
        if (drone == null || medication == null) {
            return "Drone or Medication not found.";
        }
        if (drone.getMedications().contains(medication)) {
            return "This medication is already loaded on the drone.";
        }
        if (drone.getWeightLimit() < medication.getWeight()) {
            return "Medication weight exceeds drone's weight limit.";
        }
        if (drone.getRemainingWeightCapacity() < medication.getWeight()) {
            return "Medication weight more that drone remaining weight capacity.";
        }
        int LOW_NEED_TO_CHARGE = 25;
        if (drone.getBatteryCapacity() < LOW_NEED_TO_CHARGE) {
            return "Drone battery low choose, need to charge.";
        }
        double remaneWeight = drone.getRemainingWeightCapacity() - medication.getWeight();
        drone.setRemainingWeightCapacity(remaneWeight);
        if (remaneWeight == 0) {
            drone.setState(DroneState.LOADED);
        } else {
            drone.setState(DroneState.LOADING);
        }
        drone.getMedications().add(medication);
        droneRepository.save(drone);
        return "Medication loaded successfully.";
    }

    public Set<Medication> getLoadedMedication(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        return drone != null ? drone.getMedications() : new HashSet<>();
    }

    public List<Drone> findAvailableForLoading() {
        List<DroneState> availableToLoadStates = new ArrayList<>();
        availableToLoadStates.add(DroneState.IDLE);
        availableToLoadStates.add(DroneState.LOADING);
        return droneRepository.findByStates(availableToLoadStates);
    }

    public Integer findDroneBatteryLevelById(Long droneId) {
        return droneRepository.findDroneBatteryCapacityById(droneId);
    }

    private boolean isValidWeightLimit(double weightLimit) {
        return weightLimit <= 500.0;
    }


}
