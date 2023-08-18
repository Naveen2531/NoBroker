package com.nobroker.api.controller;

import com.nobroker.api.payload.PropertyDTO;
import com.nobroker.api.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO savedProperty = propertyService.saveProperty(propertyDTO);

        if (savedProperty != null) {
            return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/set-available")
    public ResponseEntity<String> setPropertyAvailable(@PathVariable Long id) {
        boolean updated = propertyService.setPropertyAvailable(id);

        if (updated) {
            return new ResponseEntity<>("Property status set to 'Available'", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update property status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<PropertyDTO>> getApprovedProperties() {
        List<PropertyDTO> approvedProperties = propertyService.getApprovedProperties();
        return new ResponseEntity<>(approvedProperties, HttpStatus.OK);
    }
}

