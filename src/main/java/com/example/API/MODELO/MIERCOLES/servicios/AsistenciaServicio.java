package com.example.API.MODELO.MIERCOLES.servicios;
import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.mapas.IMapaAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.modelos.mapas.IMapaRegistroAsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.AsistenciaRepositorio;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IAsistenciaRepositorio;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaServicio {

    @Autowired
    private IAsistenciaRepositorio asistenciaRepo;

    @Autowired
    private IEstudianteRepositorio estudianteRepo;

    @Autowired
    IMapaRegistroAsistenciaDTO mapa;

    public AsistenciaDTO registarAsistencia(Asistencia datosAsistencia, Asistencia asistencia)throws Exception{

        try {
            if (asistencia.getFecha() == null) {
                throw new IllegalArgumentException("La fecha de asistencia no puede estar vacía.");
            }

            if (asistencia.getFecha().isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de asistencia no puede ser futura.");
            }

            return  mapa.convertirResgistroAsistenciaDTO(asistenciaRepo.save(datosAsistencia));
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion() +error.getMessage());
        }
    }


}
