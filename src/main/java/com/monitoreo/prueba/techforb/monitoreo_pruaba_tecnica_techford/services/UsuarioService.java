package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Rol;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.usuarios.Usuario;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.CantidadCaracteresException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.EmailExistenteException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.FormatoEmailException;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.exceptions.UsuarioNoEncontradoExceotion;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario.RolRepository;
import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.repositories.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private static final String EMAIL_PATRON = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATRON);


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @Transactional
    public ResponseEntity<?> save(Usuario usuario) throws EmailExistenteException, FormatoEmailException, CantidadCaracteresException{
        Optional<Rol> optionalRol = rolRepository.findById(1L);
        Set<Rol> roles = new HashSet<>();
        optionalRol.ifPresent(rol->roles.add(rol));
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailExistenteException("Email ya en uso");
        }else if(!validacionEmail(usuario.getEmail())){
            throw new FormatoEmailException("No ha ingresado un email valido");
        }
        if(usuario.getContrasenia().length()>8){
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("status", 201);
            respuesta.put("mensaje", "Usuario creado con exito");
            respuesta.put("usuario", usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }else{
            throw new CantidadCaracteresException("Contraseña muy corta, debe contar con al menos 8 carcateres");
        }

    }

    @Transactional
    public ResponseEntity<?> buscarUsuario(String email) throws UsuarioNoEncontradoExceotion{
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        
            Usuario usuario = usuarioOptional.orElseThrow(()->new UsuarioNoEncontradoExceotion("Usuario no encontrado"));
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("status", 302);
            respuesta.put("mensaje","Usuario encontontrado");
            respuesta.put("usuario", usuario);
            return ResponseEntity.status(HttpStatus.FOUND).body(respuesta);
        
        
    }


    public boolean validacionEmail(String email){   
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
