package com.me.sensor.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.sensor.models.WCharacter;


@Repository
public interface CharacterRepository extends JpaRepository<WCharacter, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
}