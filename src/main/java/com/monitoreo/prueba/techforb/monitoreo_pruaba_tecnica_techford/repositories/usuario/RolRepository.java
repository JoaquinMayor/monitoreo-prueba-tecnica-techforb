package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Rol;
import java.util.Optional;

@Repository
public interface RolRepository extends CrudRepository<Rol,Long> {

    @SuppressWarnings("null")
    Optional<Rol> findById(Long id);
}
