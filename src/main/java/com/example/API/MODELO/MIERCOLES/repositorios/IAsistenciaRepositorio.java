package com.example.API.MODELO.MIERCOLES.repositorios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {

    List<Asistencia> findByEstudianteId(Integer estudianteId);

    List<Asistencia> findByFecha(LocalDate fecha);

}
