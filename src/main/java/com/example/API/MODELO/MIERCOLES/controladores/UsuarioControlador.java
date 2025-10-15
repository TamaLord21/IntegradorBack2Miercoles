package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios") //BAUTIZAR EL API
public class UsuarioControlador {

    //Llamar al servicio
    @Autowired
    UsuarioControlador servicio;

    //ACTIVAMOS LAS RESPUESTAS AL CLIENTE(PUSSIES DEL FRONT)

    //1. Respuesta del API para peticion de guardado
    @PostMapping
    public ResponseEntity<?> operacionGuardado(@RequestBody Usuario datos){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.operacionGuardado(datos));
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
                    .body(servicio.operacionBuscarTodos());
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }


}
