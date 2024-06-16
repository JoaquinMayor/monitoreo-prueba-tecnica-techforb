package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Alerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;

@Repository
public interface AlertaRepository extends CrudRepository<Alerta,Long>{

    Optional<Alerta> findById(Long id);
    
    @Query("SELECT COUNT(a) FROM Alerta a WHERE a.alerta = :tipo")
    Optional<Alerta> finByAlerta(@Param("tipo") TipoAlerta tipo);
}
