package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    Optional<Usuario> findById(Long id);
}
