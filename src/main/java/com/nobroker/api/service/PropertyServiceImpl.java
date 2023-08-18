package com.nobroker.api.service;

import com.nobroker.api.entity.Property;
import com.nobroker.api.payload.PropertyDTO;
import com.nobroker.api.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Property property = mapToEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);
        return mapToDto(savedProperty);
    }

    private Property mapToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setApartmentType(propertyDTO.getApartmentType());
        property.setBhkType(propertyDTO.getBhkType());
        property.setFloor(propertyDTO.getFloor());
        property.setTotalFloor(propertyDTO.getTotalFloor());
        property.setPropertyAge(propertyDTO.getPropertyAge());
        property.setFacing(propertyDTO.getFacing());
        property.setBuiltUpArea(propertyDTO.getBuiltUpArea());
        property.setStatus("pending");
        return property;
    }

    private PropertyDTO mapToDto(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setApartmentType(property.getApartmentType());
        propertyDTO.setBhkType(property.getBhkType());
        propertyDTO.setFloor(property.getFloor());
        propertyDTO.setTotalFloor(property.getTotalFloor());
        propertyDTO.setPropertyAge(property.getPropertyAge());
        propertyDTO.setFacing(property.getFacing());
        propertyDTO.setBuiltUpArea(property.getBuiltUpArea());
        propertyDTO.setStatus(property.getStatus());
        return propertyDTO;
    }

    @Override
    public boolean setPropertyAvailable(Long id) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            property.setStatus("Available");
            propertyRepository.save(property);
            return true;
        }
        return false;
    }

    @Override
    public List<PropertyDTO> getApprovedProperties() {
        List<Property> approvedProperties = propertyRepository.findByStatus("Available");
        List<PropertyDTO> approvedPropertyDTOs = new ArrayList<>();

        for (Property property : approvedProperties) {
            approvedPropertyDTOs.add(mapToDto(property));
        }

        return approvedPropertyDTOs;
    }
}
