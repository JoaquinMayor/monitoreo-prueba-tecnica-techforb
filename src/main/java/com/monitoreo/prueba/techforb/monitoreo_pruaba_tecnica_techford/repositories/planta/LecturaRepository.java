package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Lectura;


public interface LecturaRepository extends CrudRepository<Lectura,Long>{

    Optional<Lectura> findById(Long id);

}
