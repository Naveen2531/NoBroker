package com.nobroker.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int apartmentType;
    private int bhkType;
    private int floor;
    private int totalFloor;
    private int propertyAge;
    private String facing;
    private double builtUpArea;
    private String status;
}
