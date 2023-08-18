package com.nobroker.api.service;

import com.nobroker.api.payload.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    boolean setPropertyAvailable(Long id);
    List<PropertyDTO> getApprovedProperties();
}


