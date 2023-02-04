package com.example.drone.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Author: Sergey.
 */
@Getter
@Setter
@Entity
public class Medication extends BaseEntity {

    private String code;
    private String name;
    private double weight;
    private String image;
}
