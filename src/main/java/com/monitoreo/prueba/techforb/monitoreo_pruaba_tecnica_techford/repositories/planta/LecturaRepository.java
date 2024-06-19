package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Lectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;

@Repository
public interface LecturaRepository extends CrudRepository<Lectura,Long>{
    
    @SuppressWarnings("null")
    Set<Lectura> findAll();
    @SuppressWarnings("null")
    Optional<Lectura> findById(Long id);

    @Query("SELECT COUNT(l) FROM Lectura l WHERE l.alerta.alerta = :tipoAlerta")
    Integer contarLecturasPorTipo(@Param("tipoAlerta") TipoAlerta tipoAlerta);

    @Query("SELECT COUNT(l) FROM Lectura l WHERE l.tipo.tipo = :tipoLecturaNombre AND l.alerta.alerta = :tipoAlertaNombre")
    int contraLecturasPorTipoLecturaYAlerta(@Param("tipoLecturaNombre") String tipoLecturaNombre, @Param("tipoAlertaNombre") String tipoAlertaNombre);

}
