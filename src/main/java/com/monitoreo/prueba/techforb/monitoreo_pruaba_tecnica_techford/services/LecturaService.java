package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Lectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.LecturaRepository;

@Service
public class LecturaService {

    @Autowired
    private LecturaRepository lecturaRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        Map<String, Object> respuesta = new HashMap<>();
        Set<Lectura> setLecturas = lecturaRepository.findAll();
        respuesta.put("status", 302);
        respuesta.put("mensaje","Todas las lecturas obtenidas");
        respuesta.put("lecturas", setLecturas);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> contarLecturasOk(){
        Map<String, Object> respuesta = new HashMap<>();
        Integer cantidad = lecturaRepository.contarLecturasPorTipo(TipoAlerta.OK);
        respuesta.put("status", 200);
        respuesta.put("mensaje","Todas las lecturas contadas");
        respuesta.put("cant", cantidad);
        return ResponseEntity.status(200).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> contarLecturasMedia(){
        Map<String, Object> respuesta = new HashMap<>();
        Integer cantidad = lecturaRepository.contarLecturasPorTipo(TipoAlerta.MEDIAS);
        respuesta.put("status", 200);
        respuesta.put("mensaje","Todas las lecturas contadas");
        respuesta.put("cant", cantidad);
        return ResponseEntity.status(200).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> contarLecturasRojas(){
        Map<String, Object> respuesta = new HashMap<>();
        Integer cantidad = lecturaRepository.contarLecturasPorTipo(TipoAlerta.ROJAS);
        respuesta.put("status", 200);
        respuesta.put("mensaje","Todas las lecturas contadas");
        respuesta.put("cant", cantidad);
        return ResponseEntity.status(200).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> contarLecturasPorTipo(String tipoLectura, String tipoAlerta){
        Map<String, Object> respuesta = new HashMap<>();
        Integer cantidad = lecturaRepository.contraLecturasPorTipoLecturaYAlerta(tipoLectura, tipoAlerta);
        respuesta.put("status", 200);
        respuesta.put("mensaje","Todas las lecturas contadas");
        respuesta.put("cant", cantidad);
        return ResponseEntity.status(200).body(respuesta);
    }
}
