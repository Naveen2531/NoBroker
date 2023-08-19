package com.nobroker.api.service;

import com.nobroker.api.payload.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    boolean setPropertyAvailable(long id);
    List<PropertyDTO> getApprovedProperties();
}


