package com.example.API.MODELO.MIERCOLES.servicios;


import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.EstudianteEspecialDTO;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.UsuarioDTO;
import com.example.API.MODELO.MIERCOLES.modelos.mapas.IMapaEstudianteDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
