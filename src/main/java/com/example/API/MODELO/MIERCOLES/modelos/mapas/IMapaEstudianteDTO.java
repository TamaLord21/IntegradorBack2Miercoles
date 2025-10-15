package com.example.API.MODELO.MIERCOLES.modelos.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Estudiante;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.EstudianteEspecialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaEstudianteDTO {
    IMapaEstudianteDTO INSTANCE= Mappers.getMapper(IMapaEstudianteDTO.class);


    //Mapeando un modelo a DTO
    @Mapping(source="usuario.nombre", target="nombre")
    @Mapping(source="usuario.contraseña", target="contraseña")
    @Mapping(source="usuario.rol", target="rol")
    @Mapping(source="promedio", target="promedio")
    @Mapping(source="fechaNacimiento", target="fechaNacimiento")
    EstudianteEspecialDTO convertir_modelo_a_dto(Estudiante estudiante);

    //Mapeando una LISTA de datos del modelo
    //a una lista de datos de tipo DTO
    List<EstudianteEspecialDTO> convertir_lista_a_dto(List<Estudiante>lista);

}
