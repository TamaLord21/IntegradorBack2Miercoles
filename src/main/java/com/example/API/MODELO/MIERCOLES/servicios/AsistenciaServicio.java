package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.mapas.IMapaAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IAsistenciaRepositorio;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServicio {

    @Autowired
    private IAsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;

    @Autowired
    private IMapaAsistenciaDTO mapa;

    // Guardar asistencia
    public AsistenciaDTO guardarAsistencia(Asistencia datosAsistencia, Integer idEstudiante) throws Exception {
        try {
            Optional<Estudiante> estudianteBuscado = estudianteRepositorio.findById(idEstudiante);

            if (estudianteBuscado.isEmpty()) {
                throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + " - Estudiante no encontrado");
            }

            datosAsistencia.setEstudiante(estudianteBuscado.get());
            return mapa.convertirADTO(asistenciaRepositorio.save(datosAsistencia));

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + error.getMessage());
        }
    }

    // Buscar todas las asistencias
    public List<AsistenciaDTO> buscarTodasLasAsistencias() throws Exception {
        try {
            return mapa.convertirListaDTO(asistenciaRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + error.getMessage());
        }
    }

    // Buscar asistencia por ID
    public AsistenciaDTO buscarAsistenciaPorId(Integer id) throws Exception {
        try {
            Optional<Asistencia> asistenciaBuscada = asistenciaRepositorio.findById(id);
            if (asistenciaBuscada.isPresent()) {
                return mapa.convertirADTO(asistenciaBuscada.get());
            } else {
                throw new Exception(MensajeError.ERROR_ASISTENCIA_NO_ENCONTRADA.getDescripcion());
            }
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + error.getMessage());
        }
    }

    // Listar asistencias por estudiante
    public List<AsistenciaDTO> listarAsistenciasPorEstudiante(Integer idEstudiante) throws Exception {
        try {
            // Validar si el estudiante existe
            if (!estudianteRepositorio.existsById(idEstudiante)) {
                throw new Exception("Estudiante no encontrado con ID: " + idEstudiante);
            }

            // Obtener asistencias y mapear a DTO
            List<Asistencia> asistencias = asistenciaRepositorio.findByEstudiante_Id(idEstudiante);
            return mapa.convertirListaDTO(asistencias);

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + " - " + error.getMessage());
        }
    }

    public List<AsistenciaDTO> listarAsistenciasPorGrupoYFecha(Integer idGrupo, LocalDate fecha) throws Exception {
        try {
            List<Asistencia> asistencias = asistenciaRepositorio.findByGrupoYFecha(idGrupo, fecha);

            if (asistencias.isEmpty()) {
                throw new Exception("No se encontraron asistencias para el grupo " + idGrupo + " en la fecha " + fecha);
            }

            return mapa.convertirListaDTO(asistencias);

        } catch (Exception error) {
            throw new Exception("Error al listar asistencias por grupo y fecha: " + error.getMessage());
        }
    }

    public AsistenciaDTO actualizarEstadoAsistencia(int id, EstadosAsistencia nuevoEstado) throws Exception {

        Optional<Asistencia> asistenciaOptional = asistenciaRepositorio.findById(id);
        if (asistenciaOptional.isEmpty()) {
            throw new Exception(" No se encontró la asistencia con ID: " + id);
        }

        Asistencia asistencia = asistenciaOptional.get();


        asistencia.setEstado(nuevoEstado);


        Asistencia asistenciaActualizada = asistenciaRepositorio.save(asistencia);


        AsistenciaDTO dto = new AsistenciaDTO();
        dto.setFecha(asistenciaActualizada.getFecha());
        dto.setObservacion(asistenciaActualizada.getObservacion());
        // 🔹 Si quieres incluir el estado en el DTO, agrégalo también:
        // dto.setEstado(asistenciaActualizada.getEstado().toString());

        return dto;
    }

    }




