package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.modelos.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.servicios.AsistenciaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AsistenciaControlador {

    private final AsistenciaServicio asistenciaServicio;

    public AsistenciaControlador(AsistenciaServicio asistenciaServicio) {
        this.asistenciaServicio = asistenciaServicio;
    }

    @PostMapping("/estudiantes/{estudianteId}/asistencias")
    public ResponseEntity<AsistenciaDTO> registrar(
            @PathVariable Integer estudianteId,
            @RequestBody AsistenciaDTO dto) throws Exception {

        AsistenciaDTO respuesta = asistenciaServicio.registrarAsistencia(estudianteId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta); // 201
    }
}