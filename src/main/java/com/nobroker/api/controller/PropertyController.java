package com.nobroker.api.controller;

import com.nobroker.api.entity.LegalServicePackage;
import com.nobroker.api.entity.LegalServicePayment;
import com.nobroker.api.payload.PropertyDTO;
import com.nobroker.api.service.PropertyService;
import com.nobroker.api.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final StripeService stripeService;

    @Autowired
    public PropertyController(PropertyService propertyService, StripeService stripeService) {
        this.propertyService = propertyService;
        this.stripeService = stripeService;
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

    @PostMapping("/make-payment")
    public ResponseEntity<Map<String, String>> makePayment(@RequestBody LegalServicePayment paymentRequest) {
        try {
            LegalServicePackage selectedPackage = paymentRequest.getSelectedPackage();
            long amount = selectedPackage.getPackagePrice();

            PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount);
            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}

