package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Sensor;

import org.springframework.data.repository.CrudRepository;

//Repositorio que accede a la información de los sensores en la base de datos.
@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long>{

    //Cuenta la cantidad de sensores deshabilitados que existen en la base de datos.
    @Query("SELECT COUNT(s) FROM Sensor s WHERE s.habilitado = false")
    Integer contraSensoresDesabilitados();

    //Cuenta la cantidad de sensores habilitados en la base de datos.
    @Query("SELECT COUNT(s) FROM Sensor s WHERE s.habilitado = true")
    Integer contraSensoresHabilitados();

    //Devuelve todos los sensores almacenados en la base de datos.
    @SuppressWarnings("null")
    Set<Sensor> findAll();
}
