package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
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


