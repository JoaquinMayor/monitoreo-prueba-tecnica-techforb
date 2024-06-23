package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;
//Repositorio que accede a la información de los usuarios en la base de datos.
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    //Devuelve un usuario buscado por su id en la base de datos.
    @SuppressWarnings("null")
    Optional<Usuario> findById(Long id);

    //Devuelve un usuario buscado por su email en la base de datos.
    Optional<Usuario> findByEmail(String email);

    //Devuelve un boolean en base a si existe un usuario pasandole su email.
    boolean existsByEmail(String email);
}
