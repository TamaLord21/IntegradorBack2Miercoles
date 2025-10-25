package com.example.API.MODELO.MIERCOLES.repositorios;

import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGruposRepositorio extends JpaRepository<Grupos, Integer> {
}
