package com.example.drone.management.repository;

import com.example.drone.management.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Author: Sergey.
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
}