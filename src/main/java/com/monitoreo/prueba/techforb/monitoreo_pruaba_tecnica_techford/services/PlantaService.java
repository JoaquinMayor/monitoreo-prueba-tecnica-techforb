package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Alerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Lectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.Planta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.TipoLectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.AlertaNoEncontradaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadNoLogicaException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.TipoAlertaNEncontradoException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.AlertaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.LecturaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.PlantaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.TipoLecturaRepository;


@Service
public class PlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    @Autowired
    private LecturaRepository lecturaRepository;
    
    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private TipoLecturaRepository tipoLecturaRepository;

    @Transactional
    public ResponseEntity<?> save(Planta planta){
        plantaRepository.save(planta);
        Map<String, Object> respuesta = new HashMap<>();  
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("mensaje", "Planta agregada con Éxito");
        respuesta.put("planta", planta);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Transactional
    public ResponseEntity<?> cargarUnaLectura(Lectura lectura, Long idPlanta){
        Optional<Planta> optionalPlanta = plantaRepository.findById(idPlanta);
        Map<String, Object> respuesta = new HashMap<>();
        if(optionalPlanta.isPresent()){
            Planta planta = optionalPlanta.get();
            planta.setLectura(lectura);
            lectura.setPlanta(planta);
            plantaRepository.save(planta);
            lecturaRepository.save(lectura);
            respuesta.put("status", 200);
            respuesta.put("mensaje","Planta actualizada");
        }else{
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("mensaje","La planta que intenta actualizar no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.status(200).body(respuesta);
    }


    @Transactional
    public ResponseEntity<?> actualizarCantidadLecturas(Long idPlanta, 
            Integer cantLecturas, Integer cantLectOk, Integer cantLectMedio, Integer cantLectRojo) throws AlertaNoEncontradaException, CantidadNoLogicaException, TipoAlertaNEncontradoException{
        
        Optional<Planta> optionalPlanta = plantaRepository.findById(idPlanta);
        Map<String, Object> respuesta = new HashMap<>();

        if(optionalPlanta.isPresent()){
            Planta planta = optionalPlanta.get();
            
            if(cantLecturas == cantLectOk + cantLectMedio+ cantLectRojo){
                for(int o= 0 ; o<cantLectOk;o++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(TipoAlerta.OK));
                    planta.setLectura(lectura);
                    lecturaRepository.save(lectura);  
                }
                for(int m= 0 ; m<cantLectMedio;m++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(TipoAlerta.MEDIAS));
                    planta.setLectura(lectura);
                    lecturaRepository.save(lectura);
                }
                for(int r= 0 ; r<cantLectRojo;r++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(TipoAlerta.ROJAS));
                    planta.setLectura(lectura);
                    lecturaRepository.save(lectura);
                }
                plantaRepository.save(planta);
                respuesta.put("status", 200);
                respuesta.put("mensaje", "Actualizado con exito");
                respuesta.put("planta", planta);
                return ResponseEntity.status(200).body(respuesta);
            }else{
                throw new CantidadNoLogicaException("La cantidad de situaciones agregadas no corresponde con la cantidad lecturas ingresadas");
            }

        }else{
            respuesta.put("status", HttpStatus.BAD_REQUEST);
            respuesta.put("mensaje", "Planta no encontrada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        

    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        Map<String, Object> respuesta = new HashMap<>();
        Set<Planta> setPlantas = plantaRepository.findAll();
        respuesta.put("status", HttpStatus.FOUND);
        respuesta.put("mensaje","Todas la plantas obtenidas");
        respuesta.put("plantas", setPlantas);
        return ResponseEntity.status(HttpStatus.FOUND).body(respuesta);
    }


    public Alerta cargarAlerta(TipoAlerta tipo) throws AlertaNoEncontradaException{
        Alerta alerta = alertaRepository.finByAlerta(tipo).orElseThrow(()-> new AlertaNoEncontradaException("Tipo de alerta no encontrada"));
        
        return alerta;
    }

    public TipoLectura determinarTipoLectura() throws TipoAlertaNEncontradoException{
        Random random = new Random();
        Long idLectura = random.nextLong(8) + 1L;
        TipoLectura tipo = tipoLecturaRepository.findById(idLectura).orElseThrow(()->new TipoAlertaNEncontradoException("No se encontró ese tipo de Alerta"));;
        return tipo;
    }

}
