package com.example.drone.management.service;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.entity.DroneState;
import com.example.drone.management.repository.DroneRepository;
import com.example.drone.management.repository.MedicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Author: Sergey.
 */
@SpringBootTest
class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    private DroneService droneService;

    private Drone drone;

    private List<Drone> expectedDrones;

    @BeforeEach
    public void setUp() {
        droneService = new DroneService(droneRepository, medicationRepository);
        drone = new Drone();
        drone.setSerialNumber("testDrone");
        drone.setId(1L);

        List<Drone> expectedDrones = new ArrayList<>();
        Drone drone2 = new Drone();
        drone2.setSerialNumber("testDrone");
        drone2.setId(2L);
        drone2.setState(DroneState.IDLE);
        Drone drone3 = new Drone();
        drone3.setSerialNumber("testDrone");
        drone3.setId(3L);
        drone3.setState(DroneState.LOADING);
        expectedDrones.add(drone);
        expectedDrones.add(drone);

    }


    @Test
    void testGet() {
        Optional<Drone> optionalDrone = Optional.of(drone);
        when(droneRepository.findById(1L)).thenReturn(optionalDrone);
        Optional<Drone> byId = droneRepository.findById(drone.getId());
        Assertions.assertEquals(drone.getId(), byId.get().getId());
    }

    @Test
    void testFindAvailableForLoading(){
        List<DroneState> availableToLoadStates = new ArrayList<>();
        availableToLoadStates.add(DroneState.IDLE);
        availableToLoadStates.add(DroneState.LOADING);
        when(droneRepository.findByStates(availableToLoadStates)).thenReturn(expectedDrones);
        List<Drone> actualDrones = droneService.findAvailableForLoading();
        Assertions.assertEquals(expectedDrones, actualDrones);
    }

}