package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario.UsuarioRepository;

//Servicio que maneja las validaciones y generacion de token.
@Service
public class JpaUserDEtailsService implements UserDetailsService{
  
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
       Optional<Usuario> userOptional =repository.findByEmail(email);
       if(userOptional.isEmpty()){
        throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", email));
       }
       Usuario user = userOptional.orElseThrow();
       List<GrantedAuthority> authorities = user.getRoles().stream()
       .map(role-> new SimpleGrantedAuthority(role.getName()))
       .collect(Collectors.toList());
       
       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getContrasenia(), 
       user.isEnabled(),true,true,true,authorities);
    }
}
