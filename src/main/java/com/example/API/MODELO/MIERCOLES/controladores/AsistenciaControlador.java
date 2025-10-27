package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.servicios.AsistenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio asistenciaServicio;

    @PostMapping
    public ResponseEntity<?> registrarAsistencia(@RequestBody Asistencia datos) {
        try {
            AsistenciaDTO respuesta = asistenciaServicio.registarAsistencia(datos, datos);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
