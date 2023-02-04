package com.example.drone.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Author: Sergey.
 */
@Getter
@Setter
@Entity
public class Drone extends BaseEntity {

    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private double weightLimit;
    private int batteryCapacity;
    private String state;
}
