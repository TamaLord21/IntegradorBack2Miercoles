package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.dtos.UsuarioDTO;
import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/usuarios") //BAUTIZAR EL API
public class UsuarioControlador {

    //Llamar al servicio
    @Autowired
    UsuarioServicio servicio;

    //ACTIVAMOS LAS RESPUESTAS AL CLIENTE(PUSSIES DEL FRONT)

    //1. Respuesta del API para peticion de guardado
    @PostMapping
    public ResponseEntity<?> operacionGuardado(@RequestBody Usuario datos){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.guardarUsuario(datos));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<?> operacionBuscarTodos(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.buscarTodosLosUsuarios());
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario datos) {
        try {

            // === DEBUG ===
            System.out.println("Correo recibido del frontend: '" + datos.getCorreo() + "'");
            System.out.println("Contraseña recibida del frontend: '" + datos.getContraseña() + "'");
            System.out.println("Longitud correo: " + (datos.getCorreo() != null ? datos.getCorreo().length() : "null"));
            System.out.println("Longitud contraseña: " + (datos.getContraseña() != null ? datos.getContraseña().length() : "null"));
            // === FIN DEBUG ===

            // Buscamos el usuario en la lista usando correo y contraseña
            UsuarioDTO authDTO = servicio.buscarPorCorreoYContrasena(datos.getCorreo(), datos.getContraseña());

            if (authDTO != null) {
                // Creamos un objeto Usuario para devolverlo (puede ser la misma entidad que tu DTO)
                Usuario usuario = new Usuario();
                usuario.setNombre(authDTO.getNombre());
                usuario.setCorreo(authDTO.getCorreo());
                usuario.setContraseña(authDTO.getContraseña());
                usuario.setRol(authDTO.getRol());

                return ResponseEntity.status(HttpStatus.OK).body(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }

        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

}




