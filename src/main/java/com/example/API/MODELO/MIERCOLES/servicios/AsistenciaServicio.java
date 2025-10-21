package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
<<<<<<< HEAD
import com.example.API.MODELO.MIERCOLES.repositorios.AsistenciaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AsistenciaServicio {

    private final AsistenciaRepositorio asistenciaRepositorio;

    public AsistenciaServicio(AsistenciaRepositorio asistenciaRepositorio) {
        this.asistenciaRepositorio = asistenciaRepositorio;
    }

    public Asistencia registrarAsistencia(Asistencia asistencia) {


        if (asistencia.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de asistencia no puede estar vacía.");
        }

        if (asistencia.getFecha().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de asistencia no puede ser futura.");
        }


        return asistenciaRepositorio.save(asistencia);
    }
}
=======
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
    private ModelMapper modelMapper;

    public AsistenciaDTO registrarAsistencia(Integer estudianteId, AsistenciaDTO dto) throws Exception {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new Exception("Estudiante no encontrado con id: " + estudianteId));

        Asistencia asistencia = modelMapper.map(dto, Asistencia.class);
        asistencia.setEstudiante(estudiante);

        Asistencia guardada = asistenciaRepo.save(asistencia);

        return modelMapper.map(guardada, AsistenciaDTO.class);
    }
}


>>>>>>> feature/ramadetrabajo
