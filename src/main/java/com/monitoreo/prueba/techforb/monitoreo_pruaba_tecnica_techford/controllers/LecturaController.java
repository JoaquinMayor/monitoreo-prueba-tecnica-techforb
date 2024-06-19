package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services.LecturaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/lectura")
public class LecturaController {

    @Autowired
    private LecturaService lecturaService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return lecturaService.findAll();
    }

    @GetMapping("/lecturasOk")
    public ResponseEntity<?> contarLecturasOk(){
        return lecturaService.contarLecturasOk();
    }

    @GetMapping("/lecturasMedia")
    public ResponseEntity<?> contarLecturasMedia(){
        return lecturaService.contarLecturasMedia();
    }

    @GetMapping("/lecturasRoja")
    public ResponseEntity<?> contarLecturaRoja(){
        return lecturaService.contarLecturasRojas();
    }

    @GetMapping("/lecturasPorTipo/{tipoLectura}/{tipoAlerta}")
    public ResponseEntity<?> contarLecturaPorTipo(@PathVariable String tipoLectura, @PathVariable String tipoAlerta){
        return lecturaService.contarLecturasPorTipo(tipoLectura, tipoAlerta);
    }

    

}
