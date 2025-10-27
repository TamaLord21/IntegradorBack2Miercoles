package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.ResumenAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.modelos.mapas.IMapaAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.AsistenciaRepositorio;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PorcentajeAsistenciaServicio {

    @Autowired
    private static AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private static IMapaAsistenciaDTO mapper;

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;
    public static ResumenAsistenciaDTO obtenerPorcentajeAsistencia(Integer estudianteId) {

        List<Asistencia> asistencias = asistenciaRepositorio.findByEstudianteId(estudianteId);

        if (asistencias.isEmpty()) {
            throw new RuntimeException("No hay registros de asistencia para este estudiante.");
        }

        long totalPresentes = asistencias.stream()
                .filter(a -> a.getEstado() == EstadosAsistencia.Presente)
                .count();

        long totalAusentes = asistencias.stream()
                .filter(a -> a.getEstado() == EstadosAsistencia.Ausente)
                .count();

        double porcentaje = (totalPresentes * 100.0) / asistencias.size();
        porcentaje = Math.round(porcentaje * 100.0) / 100.0;

        ResumenAsistenciaDTO dto = mapper.convertirADTO(asistencias.get(0));
        dto.setTotalPresentes((int) totalPresentes);
        dto.setTotalAusentes((int) totalAusentes);
        dto.setResultado(porcentaje);

        return dto;
    }

    public List<ResumenAsistenciaDTO> obtenerResumenGeneral() {
        // Obtener todos los estudiantes
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();

        List<ResumenAsistenciaDTO> resumenes = new ArrayList<>();

        for (Estudiante estudiante : estudiantes) {
            List<Asistencia> asistencias = asistenciaRepositorio.findByEstudianteId(estudiante.getId());

            if (asistencias.isEmpty()) {
                continue; //si el estudiante no tiene asistencias, lo omitimos
            }

            long totalPresentes = asistencias.stream()
                    .filter(a -> a.getEstado() == EstadosAsistencia.Presente)
                    .count();

            double porcentaje = (totalPresentes * 100.0) / asistencias.size();
            porcentaje = Math.round(porcentaje * 100.0) / 100.0;

            //mapeamos con la primera asistencia
            ResumenAsistenciaDTO dto = mapper.convertirADTO(asistencias.get(0));
            dto.setTotalPresentes((int) totalPresentes);
            dto.setTotalAusentes(asistencias.size() - (int) totalPresentes);
            dto.setResultado(porcentaje);

            resumenes.add(dto);
        }

        //ordenar por porcentaje ascendente
        resumenes.sort(Comparator.comparing(ResumenAsistenciaDTO::getResultado));

        return resumenes;
    }

    public Asistencia actualizarObservacion(Integer idAsistencia, String nuevaObservacion) {
        //buscar la asistencia
        Asistencia asistencia = asistenciaRepositorio.findById(idAsistencia)
                .orElseThrow(() -> new RuntimeException("No se encontró la asistencia con ID " + idAsistencia));

        //actualizar el campo (puede ser null o vacío)
        asistencia.setObservacion(nuevaObservacion);

        //guardar el cambio
        return asistenciaRepositorio.save(asistencia);
    }


}