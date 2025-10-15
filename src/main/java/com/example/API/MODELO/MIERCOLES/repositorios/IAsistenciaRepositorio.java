package com.example.API.MODELO.MIERCOLES.repositorios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {

    // Por estudiante (id)
    List<Asistencia> findByEstudianteId(Integer estudianteId);

    // Por grupo (id)
    List<Asistencia> findByGrupoId(Integer grupoId);

    // Por fecha exacta
    List<Asistencia> findByFecha(LocalDate fecha);

}
