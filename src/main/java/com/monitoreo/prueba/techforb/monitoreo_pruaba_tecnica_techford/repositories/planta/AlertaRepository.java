package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Alerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
//Repositorio que accede a la información de las alertas en la base de datos.
@Repository
public interface AlertaRepository extends CrudRepository<Alerta,Long>{
    //Devuelve una alerta buscada por id.
    @SuppressWarnings("null")
    Optional<Alerta> findById(Long id);
    //Devuelve una alerta buscada por su tipo.
    @Query("SELECT COUNT(a) FROM Alerta a WHERE a.alerta = :tipo")
    Optional<Alerta> finByAlerta(@Param("tipo") TipoAlerta tipo);
}
