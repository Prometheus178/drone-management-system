package com.example.drone.management.repository;

import com.example.drone.management.entity.Medication;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * Author: Sergey.
 */
@Repository
public interface MedicationRepository extends JpaRepositoryImplementation<Medication, Long> {

}