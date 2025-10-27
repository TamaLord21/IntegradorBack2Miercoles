package com.example.API.MODELO.MIERCOLES.servicios;


import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.dtos.EstudianteListaDTO;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.dtos.EstudianteEspecialDTO;
import com.example.API.MODELO.MIERCOLES.mapas.IMapaEstudianteDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServicio {

    @Autowired
    IEstudianteRepositorio repositorio;

    @Autowired
    IMapaEstudianteDTO mapa;

    public EstudianteEspecialDTO guardarEstudiante(Estudiante datos)throws Exception{
        try{
            return mapa.convertir_modelo_a_dto(repositorio.save(datos));

        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion() +error.getMessage());

        }
    }

    public List<EstudianteEspecialDTO> buscarTodosLosEstudiantes()throws Exception{
        try{
            return mapa.convertir_lista_a_dto(repositorio.findAll());

        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion()+error.getMessage());
        }
    }

    public List<EstudianteListaDTO> listarEstudiantesParaAsistencia() throws Exception {
        try {
            return repositorio.findAll()
                    .stream()
                    .map(e -> new EstudianteListaDTO(
                            e.getId(),
                            e.getUsuario().getNombre(),
                            e.getUsuario().getCorreo()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception error) {
            throw new Exception("Error al listar estudiantes: " + error.getMessage());
        }
    }

}
