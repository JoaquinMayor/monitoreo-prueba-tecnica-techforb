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
//Repositorio que accede a la información de las lecturas en la base de datos.
@Repository
public interface LecturaRepository extends CrudRepository<Lectura,Long>{
    //Devuelve todas las lecturas de la base de datos.
    @SuppressWarnings("null")
    Set<Lectura> findAll();

    //Devuelve una lectura buscada por su id.
    @SuppressWarnings("null")
    Optional<Lectura> findById(Long id);

    //Devuelve la cantidad de lecturas buscadas por el tipo de alerta .
    @Query("SELECT COUNT(l) FROM Lectura l WHERE l.alerta.alerta = :tipoAlerta")
    Integer contarLecturasPorTipo(@Param("tipoAlerta") TipoAlerta tipoAlerta);

    //Cuenta la cantidad de lecturas en base al tipo de lectura y al tipo de alerta.
    @Query("SELECT COUNT(l) " +
           "FROM Lectura l " +
           "WHERE l.tipo.tipo = :tipoLectura AND l.alerta.alerta = :tipoAlerta")
    int contarLecturasPorTipoYAlerta(@Param("tipoLectura") TipoLecturaEnun tipoLectura,
                                      @Param("tipoAlerta") TipoAlerta tipoAlerta);

    //Elimina las lecturas en base a la id de la planta.
    @Modifying
    @Query("DELETE FROM Lectura l WHERE l.planta.id = :plantaId")
    void deleteByPlantaId(@Param("plantaId") Long plantaId);

}
