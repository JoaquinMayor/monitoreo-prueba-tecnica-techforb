package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Sensor;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.SensorRepository;


@Service
public class SensoresService {
    @Autowired
    private SensorRepository sensorRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?>findAll(){
        Map<String, Object> respuesta = new HashMap<>();
        Set<Sensor> setSensor = sensorRepository.findAll();
        respuesta.put("status", HttpStatus.FOUND);
        respuesta.put("mensaje","Todos los sensores obtenidos");
        respuesta.put("sensores", setSensor);
        return ResponseEntity.status(HttpStatus.FOUND).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> contarSensoresDesabilitados(){
        Map<String, Object> respuesta = new HashMap<>();
        Integer cantidad = sensorRepository.contraSensoresDesabilitados();
        respuesta.put("status", HttpStatus.FOUND);
        respuesta.put("mensaje","Todos los sensores contados");
        respuesta.put("cant", cantidad);
        return ResponseEntity.status(HttpStatus.FOUND).body(respuesta);
    }
}
