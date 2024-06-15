package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.TipoLectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoLecturaEnun;

public interface TipoLecturaRepository extends CrudRepository<TipoLectura,Long>{

    Optional<TipoLectura> findById(Long id);
    Optional<TipoLectura> findByTipo(TipoLecturaEnun tipo);

}
