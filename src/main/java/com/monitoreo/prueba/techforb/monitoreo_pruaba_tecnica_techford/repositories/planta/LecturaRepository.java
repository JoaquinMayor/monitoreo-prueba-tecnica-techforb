package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Lectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoLecturaEnun;

@Repository
public interface LecturaRepository extends CrudRepository<Lectura,Long>{
    
    @SuppressWarnings("null")
    Set<Lectura> findAll();
    @SuppressWarnings("null")
    Optional<Lectura> findById(Long id);

    @Query("SELECT COUNT(l) FROM Lectura l WHERE l.alerta.alerta = :tipoAlerta")
    Integer contarLecturasPorTipo(@Param("tipoAlerta") TipoAlerta tipoAlerta);

    @Query("SELECT COUNT(l) " +
           "FROM Lectura l " +
           "WHERE l.tipo.tipo = :tipoLectura AND l.alerta.alerta = :tipoAlerta")
    int contarLecturasPorTipoYAlerta(@Param("tipoLectura") TipoLecturaEnun tipoLectura,
                                      @Param("tipoAlerta") TipoAlerta tipoAlerta);

    @Modifying
    @Query("DELETE FROM Lectura l WHERE l.planta.id = :plantaId")
    void deleteByPlantaId(@Param("plantaId") Long plantaId);

}
