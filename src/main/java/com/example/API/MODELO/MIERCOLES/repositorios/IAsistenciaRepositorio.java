package com.example.API.MODELO.MIERCOLES.repositorios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

public interface IAsistenciaRepositorio extends JpaRepository<Asistencia,Integer> {

    Optional<Asistencia>findByFecha(LocalDate fecha);

    Optional<Asistencia>findById(int Id);

    List<Asistencia> findByEstudiante_Id(Integer IdEstudiante);

    @Query("SELECT a FROM Asistencia a WHERE a.grupo.id = :idGrupo AND a.fecha = :fecha")
    List<Asistencia> findByGrupoYFecha(@Param("idGrupo") Integer idGrupo, @Param("fecha") LocalDate fecha);

    boolean existsByEstudiante_IdAndFecha(Integer idEstudiante, LocalDate fecha);
}




