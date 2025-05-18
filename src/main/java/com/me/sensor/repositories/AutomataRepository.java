package com.me.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.me.sensor.models.Automata;

public interface AutomataRepository extends JpaRepository<Automata, Long> {
}
