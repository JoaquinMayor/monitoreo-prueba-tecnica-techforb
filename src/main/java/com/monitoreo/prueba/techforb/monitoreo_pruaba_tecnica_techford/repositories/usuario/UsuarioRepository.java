package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
