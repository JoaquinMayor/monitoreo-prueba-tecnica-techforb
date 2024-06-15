package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;

public interface PlantaRepository extends CrudRepository<Planta, Long>{

    Optional<Planta> findById(Long id);
    Optional<Planta> findByNombre(String nombre);

}
