package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
//Repositorio que accede a la información de las plantas en la base de datos.
@Repository
public interface PlantaRepository extends CrudRepository<Planta, Long>{
    //Devuelve todas las plantas almacenadas en la base de datos
    @SuppressWarnings("null")
    Set<Planta> findAll();
    //Devuelve una planta buscada por su id en la base de datos
    @SuppressWarnings("null")
    Optional<Planta> findById(Long id);
    //Devuelve una planta buscada por su nombre en la base de datos
    Optional<Planta> findByNombre(String nombre);

    //Obtiene la cantidad de lecturas que tiene una planta en base a su tipo de alerta.
    @Query("SELECT COUNT(l) FROM Planta p JOIN p.lecturas l WHERE p.id = :plantaId AND l.alerta.alerta = :tipoAlerta  GROUP BY p")
    Integer obtenerCantidadLecturasPorTipo(@Param("tipoAlerta") TipoAlerta tipoAlerta, @Param("plantaId") Long plantaId);

}
