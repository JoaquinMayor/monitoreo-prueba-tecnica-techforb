package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Alerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;

public interface AlertaRepository extends CrudRepository<Alerta,Long>{

    Optional<Alerta> findById(Long id);
    Optional<Alerta> finByAlerta(TipoAlerta tipo);
}
