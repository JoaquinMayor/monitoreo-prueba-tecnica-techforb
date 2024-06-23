package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.AlertaNoEncontradaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadCaracteresException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadNoLogicaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.EmailExistenteException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.FormatoEmailException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.TipoAlertaNEncontradoException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.UsuarioNoEncontradoExceotion;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.models.Error;


/**
 * Controlador destinado al manejo de errores.
 */
@RestControllerAdvice
public class HandlerExceptionController {
    //Maneja la excepción por si no se encuentra algún tipo de alerta
    @ExceptionHandler(AlertaNoEncontradaException.class)
    public ResponseEntity<Error> alertaNoEncontrada(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("La alerta no se pudo encontrar");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    //Maneja la excepción por si la cantidad de carcateres ingresados en la contraseña es incorrecto
    @ExceptionHandler(CantidadCaracteresException.class)
    public ResponseEntity<Error> cantidadDeCaracteres(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("La cantidad de carcateres ingresados tiene que ser superior a 8");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
//Maneja la excepción por si la cantidad de lecturas ingresdas no es  igual a la suma de sus tipos.
    @ExceptionHandler(CantidadNoLogicaException.class)
    public ResponseEntity<Error> cantidadNoLogica(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("La suma del tipo de alertas no se correlaciona con la cantidad de lecturas ingresadas");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    //Maneja la excepción por si ingresado no es valido
    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<Error> emailExistente(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("El email ingresado ya se encuentra registrado");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    //Maneja la excepción por si el formato del emmail no es válido.
    @ExceptionHandler(FormatoEmailException.class)
    public ResponseEntity<Error> formatoEmail(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("El formato ingresado con corresponde a un email válido");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    //Maneja la excepción por si no se encuentra un tipo de alerta
    @ExceptionHandler(TipoAlertaNEncontradoException.class)
    public ResponseEntity<Error> tipoAlertaNoEncontrado(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("El tipo de alerta no se pudo encoentrar");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    //Maneja la excepción por si el usuario buscado no se encuentra
    @ExceptionHandler(UsuarioNoEncontradoExceotion.class)
    public ResponseEntity<Error> usuarioNoEncontrada(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Usuario no encoentrado");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
