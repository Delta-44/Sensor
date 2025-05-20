package com.me.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.sensor.models.Asignacion;

public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
}
