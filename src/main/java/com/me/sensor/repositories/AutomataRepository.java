package com.me.sensor.repositories;

import com.me.sensor.models.Automata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomataRepository extends JpaRepository<Automata, String> {
}
