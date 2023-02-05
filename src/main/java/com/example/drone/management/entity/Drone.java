package com.example.drone.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Sergey.
 */
@Getter
@Setter
@Entity
@Table(name = "drone")
public class Drone extends BaseEntity {

    @Column(name = "serial_number", length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column
    private double weightLimit;
    @Column
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    @Column
    private DroneState state;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "drone_id")
    private Set<Medication> medications = new HashSet<>();

    @Column(name = "remaining_weight_capacity")
    private double remainingWeightCapacity;

}
