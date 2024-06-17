package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
@Repository
public interface PlantaRepository extends CrudRepository<Planta, Long>{

    @SuppressWarnings("null")
    Set<Planta> findAll();
    @SuppressWarnings("null")
    Optional<Planta> findById(Long id);
    Optional<Planta> findByNombre(String nombre);

    @Query("SELECT COUNT(l) FROM Planta p JOIN p.lecturas l WHERE p.id = :plantaId AND l.alerta.alerta = :tipoAlerta  GROUP BY p")
    Integer obtenerCantidadLecturasPorTipo(@Param("tipoAlerta") TipoAlerta tipoAlerta, @Param("plantaId") Long plantaId);

}
