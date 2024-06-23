package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Rol;
import java.util.Optional;

//Repositorio que accede a la información de los roles en la base de datos.
@Repository
public interface RolRepository extends CrudRepository<Rol,Long> {

    //Devuelve un rol buscado por su id en la base de datos.
    @SuppressWarnings("null")
    Optional<Rol> findById(Long id);
}
