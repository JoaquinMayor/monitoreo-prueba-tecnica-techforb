package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import org.springframework.data.repository.CrudRepository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Rol;
import java.util.Optional;


public interface RoleRepository extends CrudRepository<Rol,Long> {

    Optional<Rol> findById(Long id);
}
