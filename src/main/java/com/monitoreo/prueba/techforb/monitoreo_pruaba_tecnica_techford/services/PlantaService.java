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
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.models.ActualizacionPlanta;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.AlertaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.LecturaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.PlantaRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta.TipoLecturaRepository;

//Servicio que maneja toda la información de las plantas.
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

    //Método que se encarga del manejo del guardar una nueva planta.
    @Transactional
    public ResponseEntity<?> save(Planta planta){
        plantaRepository.save(planta);
        Map<String, Object> respuesta = new HashMap<>();  
        respuesta.put("status", 201);
        respuesta.put("mensaje", "Planta agregada con Éxito");
        respuesta.put("planta", planta);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
    //Método erncargado del manejo de la eliminación de una planta por su id.
    @Transactional
    public ResponseEntity<?> delete(Long id){
        Map<String, Object> respuesta = new HashMap<>();
        if(plantaRepository.existsById(id)){
            plantaRepository.deleteById(id);

            respuesta.put("status", 200);
            respuesta.put("mensaje", "Eliminado con exito");
            return ResponseEntity.status(200).body(respuesta);
        }
        respuesta.put("status", HttpStatus.NOT_FOUND.value());
        respuesta.put("mensaje", "No existe la planta seleccionada");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }
    //Método que se encarga del manejo de contar la cantidad de lecturas por el id de la planta y el tipo de lectura.
    @Transactional(readOnly = true)
    public ResponseEntity<?> contarCantidadLecturas(Long idPlanta, TipoAlerta tipo){
        Integer cant = plantaRepository.obtenerCantidadLecturasPorTipo(tipo, idPlanta);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", 200);
        respuesta.put("mensaje", "Encontrado con exito");
        respuesta.put("cant", cant);
        return ResponseEntity.status(200).body(respuesta);
    }

    //Método encargado de cargar una nueva lectura a una planta por su id.
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
            respuesta.put("status", 404);
            respuesta.put("mensaje","La planta que intenta actualizar no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.status(200).body(respuesta);
    }

    //Método encargado de actualizar la cantidad y tipos de lecturas que tiene una planta.
    @Transactional
    public ResponseEntity<?> actualizarCantidadLecturas(ActualizacionPlanta actualizacion) throws AlertaNoEncontradaException, CantidadNoLogicaException, TipoAlertaNEncontradoException{
        
        Optional<Planta> optionalPlanta = plantaRepository.findById(actualizacion.getIdPlanta());
        Map<String, Object> respuesta = new HashMap<>();

        if(optionalPlanta.isPresent()){
            Planta planta = optionalPlanta.get();
            
            if(actualizacion.getCanLecturas() == actualizacion.getCantLectOk()+ actualizacion.getCantLectMedio()+ actualizacion.getCantLectRojo()){
                if(planta.getLecturas().size()>0){
                    lecturaRepository.deleteByPlantaId(actualizacion.getIdPlanta());
                }

                for(int o= 0 ; o<actualizacion.getCantLectOk();o++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(1L));
                    planta.setLectura(lectura);
                    lecturaRepository.save(lectura);  
                }
                for(int m= 0 ; m<actualizacion.getCantLectMedio();m++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(2L));
                    planta.setLectura(lectura);
                    lecturaRepository.save(lectura);
                }
                for(int r= 0 ; r<actualizacion.getCantLectRojo();r++){
                    Lectura lectura = new Lectura();
                    lectura.setPlanta(planta);
                    lectura.setTipo(determinarTipoLectura());
                    lectura.setAlerta(cargarAlerta(3L));
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
            respuesta.put("status", 400);
            respuesta.put("mensaje", "Planta no encontrada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        

    }
    //Método que retorna un ResponseEntity con todas las plantas de la base de datos.
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        Map<String, Object> respuesta = new HashMap<>();
        Set<Planta> setPlantas = plantaRepository.findAll();
        respuesta.put("status", 302);
        respuesta.put("mensaje","Todas la plantas obtenidas");
        respuesta.put("plantas", setPlantas);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Método que retorna un ResponseEntity que retirna una planta buscada por su id.
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Planta> plantaOptional = plantaRepository.findById(id);
        if(plantaOptional.isPresent()){
            Planta planta = plantaOptional.get();
            respuesta.put("status", 302);
            respuesta.put("mensaje","Planta encontrada");
            respuesta.put("plantas", planta);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else{
            respuesta.put("status", 302);
            respuesta.put("mensaje","Planta no encontrada");
           ;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
       
    }

    //Método encargado de cargar una alerta.
    public Alerta cargarAlerta(Long id) throws AlertaNoEncontradaException{
        Alerta alerta = alertaRepository.findById(id).orElseThrow(()-> new AlertaNoEncontradaException("Tipo de alerta no encontrada"));
        
        return alerta;
    }


    //Método destinado a determinar el tipo de lectura de manera aleatoria para la carga de la cantidad de lecturas.
    public TipoLectura determinarTipoLectura() throws TipoAlertaNEncontradoException{
        Random random = new Random();
        Long idLectura = random.nextLong(8) + 1L;
        TipoLectura tipo = tipoLecturaRepository.findById(idLectura).orElseThrow(()->new TipoAlertaNEncontradoException("No se encontró ese tipo de Alerta"));;
        return tipo;
    }

}
