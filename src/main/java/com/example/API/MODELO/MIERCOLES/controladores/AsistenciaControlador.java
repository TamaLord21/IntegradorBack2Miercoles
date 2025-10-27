package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.example.API.MODELO.MIERCOLES.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.dtos.GruposDTO;
import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Grupos;
import com.example.API.MODELO.MIERCOLES.servicios.AsistenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio servicio;

    @PostMapping("/registrar/{idEstudiante}")
    public ResponseEntity<?> registrarAsistencia(@RequestBody Asistencia asistencia, @PathVariable Integer idEstudiante) {
        try {
            AsistenciaDTO nueva = servicio.guardarAsistencia(asistencia, idEstudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (Exception e) {
            String mensaje = e.getMessage();

            //error por duplicidad de asistencia
            if (mensaje != null && mensaje.contains("ya tiene registrada una asistencia")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT) // Código 409
                        .body("❌ Error: " + mensaje);
            }

            //Otros errores generales
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("⚠️ Error al registrar asistencia: " + mensaje);
        }
    }


    @GetMapping("/todas")
    public ResponseEntity<?> listarAsistencias() {
        try {
            List<AsistenciaDTO> lista = servicio.buscarTodasLasAsistencias();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            AsistenciaDTO asistencia = servicio.buscarAsistenciaPorId(id);
            return ResponseEntity.ok(asistencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    //Endpoint para listar asistencias por estudiante
    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<?> listarAsistenciasPorEstudiante(@PathVariable Integer idEstudiante) {
        try {
            List<AsistenciaDTO> asistencias = servicio.listarAsistenciasPorEstudiante(idEstudiante);
            return ResponseEntity.ok(asistencias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/grupo/{idGrupo}")
    public ResponseEntity<?> listarAsistenciasPorGrupoYFecha(
            @PathVariable Integer idGrupo,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            List<AsistenciaDTO> asistencias = servicio.listarAsistenciasPorGrupoYFecha(idGrupo, fecha);
            return ResponseEntity.ok(asistencias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    // Actualizar estado de asistencia
    @PutMapping("/{id}/estado")
    public ResponseEntity<AsistenciaDTO> actualizarEstado(
            @PathVariable int id,
            @RequestBody EstadosAsistencia nuevoEstado
    ) throws Exception {
        AsistenciaDTO dto = servicio.actualizarEstadoAsistencia(id, nuevoEstado);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/registrar/grupal/{idGrupo}")
    public ResponseEntity<?> registrarAsistenciaGrupal(
            @RequestBody List<Asistencia> asistencias,
            @PathVariable Integer idGrupo) {
        try {
            List<AsistenciaDTO> nuevas = servicio.guardarAsistenciasGrupales(asistencias, idGrupo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/grupos")
    public ResponseEntity<?> listarGruposDisponibles() {
        try {
            List<Grupos> grupos = servicio.listarGruposDisponibles();

            // Mapeamos cada grupo a un DTO limpio
            List<GruposDTO> gruposDTO = grupos.stream()
                    .map(g -> new GruposDTO(g.getId(), g.getNombre(), g.getSemestre()))
                    .toList();

            return ResponseEntity.ok(gruposDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener los grupos: " + e.getMessage());
        }
    }





}
