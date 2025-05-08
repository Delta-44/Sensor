package com.me.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.sensor.models.measures;

@Repository
public interface repo extends JpaRepository<measures, Long> {
    // Custom query methods can be defined here if needed

    
}
