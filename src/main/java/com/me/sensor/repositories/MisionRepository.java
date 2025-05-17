package com.me.sensor.repositories;

import com.me.sensor.models.Mision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MisionRepository extends JpaRepository<Mision, String> {
    List<Mision> findByDificultad(String dificultad);
    List<Mision> findByResultado(String resultado);
}
