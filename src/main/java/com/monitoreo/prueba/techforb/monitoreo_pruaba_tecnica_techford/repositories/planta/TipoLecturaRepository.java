package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.planta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas.TipoLectura;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoLecturaEnun;
//Repositorio que accede a la información de los tipos de lecturas en la base de datos.
@Repository
public interface TipoLecturaRepository extends CrudRepository<TipoLectura,Long>{

    //Devuelve un tipo de lectura buscado por su id.
    @SuppressWarnings("null")
    Optional<TipoLectura> findById(Long id);

    //Devuelve un tipo de lectura buscado por el enum del tipo de lectura.
    Optional<TipoLectura> findByTipo(TipoLecturaEnun tipo);

}
