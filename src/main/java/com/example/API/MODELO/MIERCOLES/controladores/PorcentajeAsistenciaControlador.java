package com.example.API.MODELO.MIERCOLES.controladores;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.ObservacionAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.ResumenAsistenciaDTO;

import com.example.API.MODELO.MIERCOLES.servicios.PorcentajeAsistenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class PorcentajeAsistenciaControlador {

    @Autowired
    private PorcentajeAsistenciaServicio porcentajeAsistenciaServicio;

    @GetMapping("/porcentaje/{idEstudiante}")
    public ResponseEntity<ResumenAsistenciaDTO> obtenerPorcentajeAsistencia(
            @PathVariable Integer idEstudiante) {
        ResumenAsistenciaDTO dto = PorcentajeAsistenciaServicio.obtenerPorcentajeAsistencia(idEstudiante);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/resumen-general")
    public ResponseEntity<List<ResumenAsistenciaDTO>> obtenerResumenGeneral() {
        List<ResumenAsistenciaDTO> resumenes = porcentajeAsistenciaServicio.obtenerResumenGeneral();
        return ResponseEntity.ok(resumenes);
    }

    @PutMapping("/observacion")
    public ResponseEntity<Asistencia> actualizarObservacion(@RequestBody ObservacionAsistenciaDTO dto) {
        Asistencia asistenciaActualizada = porcentajeAsistenciaServicio.actualizarObservacion(
                dto.getIdAsistencia(),
                dto.getObservacion()
        );
        return ResponseEntity.ok(asistenciaActualizada);
    }


}
