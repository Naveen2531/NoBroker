package com.nobroker.api.repository;

import com.nobroker.api.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByStatus(String Available);
    Optional<Property> findById(Long id);
}

