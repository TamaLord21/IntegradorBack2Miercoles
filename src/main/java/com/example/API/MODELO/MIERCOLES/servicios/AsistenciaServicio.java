package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.mapas.IMapaAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.modelos.Grupos;
import com.example.API.MODELO.MIERCOLES.repositorios.IAsistenciaRepositorio;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import com.example.API.MODELO.MIERCOLES.repositorios.IGruposRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private IGruposRepositorio gruposRepositorio;

    // Guardar asistencia
    public AsistenciaDTO guardarAsistencia(Asistencia datosAsistencia, Integer idEstudiante) throws Exception {
        try {
            //Buscar estudiante
            Optional<Estudiante> estudianteBuscado = estudianteRepositorio.findById(idEstudiante);

            if (estudianteBuscado.isEmpty()) {
                throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + " - Estudiante no encontrado");
            }

            // Validar duplicidad Asistencia
            LocalDate fechaAsistencia = datosAsistencia.getFecha();
            boolean existeAsistencia = asistenciaRepositorio.existsByEstudiante_IdAndFecha(idEstudiante, fechaAsistencia);

            if (existeAsistencia) {
                throw new Exception("El estudiante con ID " + idEstudiante + " ya tiene registrada una asistencia en la fecha " + fechaAsistencia);
            }

            //Asociar estudiante y guardar asistencia
            datosAsistencia.setEstudiante(estudianteBuscado.get());
            Asistencia asistenciaGuardada = asistenciaRepositorio.save(datosAsistencia);

            //Convertir a DTO y devolver
            return mapa.convertirADTO(asistenciaGuardada);

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_ASISTENCIA.getDescripcion() + " - " + error.getMessage());
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

        return dto;
    }

    public List<AsistenciaDTO> guardarAsistenciasGrupales(List<Asistencia> asistencias, Integer idGrupo) {
        // 1. Verificar que el grupo exista
        Optional<Grupos> grupoOpt = gruposRepositorio.findById(idGrupo);
        if (grupoOpt.isEmpty()) {
            throw new RuntimeException("El grupo no existe");
        }

        Grupos grupo = grupoOpt.get();
        List<AsistenciaDTO> resultado = new ArrayList<>();

        for (Asistencia asistencia : asistencias) {
            Integer idEstudiante = asistencia.getEstudiante().getId();
            LocalDate fecha = asistencia.getFecha();

            // 2. Validar duplicidad
            boolean yaExiste = asistenciaRepositorio.existsByEstudiante_IdAndFecha(idEstudiante, fecha);
            if (yaExiste) {
                throw new RuntimeException("Ya existe una asistencia para el estudiante ID "
                        + idEstudiante + " en la fecha " + fecha);
            }

            // 3. Asociar el grupo a la asistencia
            asistencia.setGrupo(grupo);

            // 4. Guardar asistencia
            Asistencia guardada = asistenciaRepositorio.save(asistencia);

            // 5. Agregar al resultado (convertido a DTO)
            resultado.add(new AsistenciaDTO(
                    guardada.getFecha(),
                    guardada.getObservacion(),
                    guardada.getEstado()
            ));
        }

        return resultado;
    }

}




