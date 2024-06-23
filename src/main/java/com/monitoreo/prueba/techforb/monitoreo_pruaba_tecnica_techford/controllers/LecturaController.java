package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoLecturaEnun;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services.LecturaService;

/**
 * Controlador que maneja la información de las lecturas.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/lectura")
public class LecturaController {

    @Autowired
    private LecturaService lecturaService;
    //Muestra todas las lecturas existentes.
    @GetMapping
    public ResponseEntity<?> listar(){
        return lecturaService.findAll();
    }
    //Muestra la cantidad de las lecturas ok.
    @GetMapping("/lecturasOk")
    public ResponseEntity<?> contarLecturasOk(){
        return lecturaService.contarLecturasOk();
    }
    //Muestra la cantidad de las lecturas medias.
    @GetMapping("/lecturasMedia")
    public ResponseEntity<?> contarLecturasMedia(){
        return lecturaService.contarLecturasMedia();
    }
    //Muestra la cantidad de las lecturas medias.
    @GetMapping("/lecturasRoja")
    public ResponseEntity<?> contarLecturaRoja(){
        return lecturaService.contarLecturasRojas();
    }
    //Muestra la cantidad de las lecturas según el tipo de lectura y el tipo de alerta.
    @GetMapping("/lecturasPorTipo")
    public ResponseEntity<?> contarLecturaPorTipo(@RequestParam ("tipoLectura") TipoLecturaEnun tipoLectura,  @RequestParam("tipoAlerta") TipoAlerta tipoAlerta){
     
        return lecturaService.contarLecturasPorTipo(tipoLectura, tipoAlerta);
    }

    

}
