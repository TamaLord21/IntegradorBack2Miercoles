package com.example.API.MODELO.MIERCOLES.repositorios;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAsistenciaRepositorio extends JpaRepository<Asistencia,Integer> {

    Optional<Asistencia>findByFecha(LocalDate fecha);




}
