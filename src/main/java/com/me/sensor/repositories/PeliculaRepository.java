package com.me.sensor.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.me.sensor.models.Pelicula;



@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // Métodos personalizados aquí (opcional)
}


