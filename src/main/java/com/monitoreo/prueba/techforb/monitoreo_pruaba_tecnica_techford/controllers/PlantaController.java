package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.AlertaNoEncontradaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadNoLogicaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.TipoAlertaNEncontradoException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.models.ActualizacionPlanta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services.PlantaService;

import jakarta.validation.Valid;

/**
 * Controlador que maneja la información de las plantas.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/planta")
public class PlantaController {

    @Autowired
    private PlantaService plantaService;
    //Almacena una nueva planta en la base de datos.
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Planta planta,BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return plantaService.save(planta);
    }
    //Devuelve todas las plantas de la base de datos.
    @GetMapping
    public ResponseEntity<?> listar(){
        return plantaService.findAll();
    }
    //Busca una planta por su id.
    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> buscarPlantaPorId(@PathVariable Long id) {
        return this.plantaService.findById(id);
    }
    
    //Actualiza las lecturas que tiene una planta a partir de las cantidades mandadas. 
    @PutMapping("/update/lecturas")
    public ResponseEntity<?> updateLecturas(@RequestBody ActualizacionPlanta actualizacionPlanta) throws AlertaNoEncontradaException, CantidadNoLogicaException, TipoAlertaNEncontradoException{
        return plantaService.actualizarCantidadLecturas(actualizacionPlanta);
    }

    
    //Actualiza la información de una planta.
    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Planta planta, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        return plantaService.save(planta);
    }
    //Elimina una planta por su id.
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        return plantaService.delete(id);
    }
    //Cuenta la cantidad de lecturas ok que tien una planta buscada por su id.
    @GetMapping("/contarOk/{id}")
    public ResponseEntity<?> contarCantidadOk(@PathVariable Long id){
        return plantaService.contarCantidadLecturas(id, TipoAlerta.OK);
    }
    //Cuenta la cantidad de lecturas media que tien una planta buscada por su id.
    @GetMapping("/contarMedia/{id}")
    public ResponseEntity<?> contarCantidadMedia(@PathVariable Long id){
        return plantaService.contarCantidadLecturas(id, TipoAlerta.MEDIAS);
    }
    //Cuenta la cantidad de lecturas rojas que tien una planta buscada por su id.
    @GetMapping("/contarRoja/{id}")
    public ResponseEntity<?> contarCantidadRoja(@PathVariable Long id){
        return plantaService.contarCantidadLecturas(id, TipoAlerta.ROJAS);
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
