package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
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
