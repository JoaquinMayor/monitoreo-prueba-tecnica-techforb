package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadCaracteresException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.EmailExistenteException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.FormatoEmailException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.UsuarioNoEncontradoExceotion;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario,BindingResult result) throws EmailExistenteException, FormatoEmailException, CantidadCaracteresException{
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return usuarioService.save(usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String email) throws UsuarioNoEncontradoExceotion{
        return usuarioService.buscarUsuario(email);
    }


    @GetMapping("/existe/{email}")
    public ResponseEntity<?> existeUsuario(@PathVariable String email){
        return usuarioService.existeUsuario(email);
    }


    private ResponseEntity<Map<String, String>> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
