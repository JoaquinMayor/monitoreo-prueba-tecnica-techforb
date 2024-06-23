package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Sensor;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services.SensorService;

import jakarta.validation.Valid;

/**
 * Controlador que maneja la información de los sensores.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;
    //Almacena un nuevo sensor en la base de datos.
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Sensor sensor,BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return sensorService.save(sensor);
    }
    //Devuelve una lista de todos los sensores.
    @GetMapping
    public ResponseEntity<?> listar(){
        return sensorService.findAll();
    }
    //Cuenta la cantiodad de sensores desabilitados.
    @GetMapping("/contar")
    public ResponseEntity<?> contarSensores(){
        return sensorService.contarSensoresDesabilitados();
    }

    //Devoluciones de errores inesperado o de parametros no pasados correctamente.
    private ResponseEntity<Map<String, String>> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{ //Da una lista de mensajesel getFieldErrors y lo recorremos para ir creando los mensajes
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors); //Siempre que se pasa un status 400 se hace un badRequest
    }

}
