package com.example.drone.management.repository;

import com.example.drone.management.entity.Drone;
import com.example.drone.management.entity.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Author: Sergey.
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query("SELECT d FROM Drone d WHERE d.state IN :states")
    List<Drone> findByStates(@Param("states") List<DroneState> states);

}