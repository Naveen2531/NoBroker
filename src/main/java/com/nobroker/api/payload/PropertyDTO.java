package com.nobroker.api.payload;

import lombok.Data;

@Data
public class PropertyDTO {
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

