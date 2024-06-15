package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;
@Repository
public interface PlantaRepository extends CrudRepository<Planta, Long>{

    Optional<Planta> findById(Long id);
    Optional<Planta> findByNombre(String nombre);

}
