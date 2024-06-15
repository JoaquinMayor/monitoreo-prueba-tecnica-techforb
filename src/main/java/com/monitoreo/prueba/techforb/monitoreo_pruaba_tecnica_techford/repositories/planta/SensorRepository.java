package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Sensor;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long>{

    @Query("SELECT COUNT(s) FROM Sensor s WHERE s.habilitado = false")
    Integer contraSensoresDesabilitados();

    @Query("SELECT COUNT(s) FROM Sensor s WHERE s.habilitado = true")
    Integer contraSensoresHabilitados();

    Set<Sensor> findAll();
}
