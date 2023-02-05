package com.example.drone.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Sergey.
 */
@Getter
@Setter
@Entity
@Table(name = "medication")
public class Medication extends BaseEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private double weight;
    @Column
    private String image;
}
