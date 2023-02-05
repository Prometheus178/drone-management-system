package com.example.drone.management.service;

import com.example.drone.management.entity.Medication;
import com.example.drone.management.exception.InvalidFieldException;
import com.example.drone.management.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Sergey.
 */
@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    public Medication createMedication(Medication medication) {
        if (!isValidFieldName(medication.getName())) {
            throw new InvalidFieldException();
        }
        return medicationRepository.save(medication);
    }

    public Medication updateMedication(Long id, Medication medication) {
        if (!isValidFieldName(medication.getName())) {
            throw new InvalidFieldException();
        }
        return medicationRepository.save(medication);
    }

    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    private boolean isValidFieldName(String fieldName) {
        return fieldName.matches("^[a-zA-Z0-9_-]+$");
    }
}
