package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//Servicio que maneja toda la información de los usuarios.
@Service
public class UsuarioService {
    //Patron de un email
    private static final String EMAIL_PATRON = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATRON);


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    //Enciptación de contraseña
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Método que almacena un usuario en la base de datos.
    @Transactional
    public ResponseEntity<?> save(Usuario usuario) throws EmailExistenteException, FormatoEmailException, CantidadCaracteresException{
        Optional<Rol> optionalRol = rolRepository.findById(1L);
        List<Rol> roles = new ArrayList<>();
        optionalRol.ifPresent(rol->roles.add(rol));
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailExistenteException("Email ya en uso");
        }else if(!validacionEmail(usuario.getEmail())){
            throw new FormatoEmailException("No ha ingresado un email valido");
        }
        if(usuario.getContrasenia().length()>8){
            usuario.setRoles(roles);
            String passwordEncoded = passwordEncoder.encode(usuario.getContrasenia());
            usuario.setContrasenia(passwordEncoded);
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
    //Método devuleve un ResponseEntity que determina si un usuario existe en la base de datos en base a su email.
    @Transactional()
    public ResponseEntity<?> existeUsuario(String email){
        boolean existe = usuarioRepository.existsByEmail(email);
        Map<String, Object> respuesta = new HashMap<>();
        if(existe){
            respuesta.put("mensaje", "Usuario Existe");
        }else{
            respuesta.put("mensaje", "Usuario no existe");
        }
        respuesta.put("status", 200);
        respuesta.put("existe", existe);
        return ResponseEntity.ok().body(respuesta);
    }

    //Método que devuelve ResponseEntity con un usuario buscado por su email.
    @Transactional
    public ResponseEntity<?> buscarUsuario(String email) throws UsuarioNoEncontradoExceotion{
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        
            Usuario usuario = usuarioOptional.orElseThrow(()->new UsuarioNoEncontradoExceotion("Usuario no encontrado"));
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("status", 302);
            respuesta.put("mensaje","Usuario encontontrado");
            respuesta.put("usuario", usuario);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        
        
    }

    //Método que determina si un email es valido.
    public boolean validacionEmail(String email){   
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
