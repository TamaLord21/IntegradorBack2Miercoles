package com.example.API.MODELO.MIERCOLES.controladores;


import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    @Autowired
    EstudianteServicio servicio;


    @PostMapping
    public ResponseEntity<?> operacionGuardado(@RequestBody Estudiante datos){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.guardarEstudiante(datos));
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
                    .body(servicio.buscarTodosLosEstudiantes());
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> obtenerListaEstudiantes() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.listarEstudiantesParaAsistencia());
        } catch (Exception error) {
            Map<String, Object> respuestaError = new HashMap<>();
            respuestaError.put("mensaje", "Error al obtener la lista de estudiantes");
            respuestaError.put("detalle", error.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError); // ✅ JSON válido
        }
    }



}
