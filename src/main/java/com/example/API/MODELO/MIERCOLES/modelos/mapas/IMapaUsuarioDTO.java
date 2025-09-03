package com.example.API.MODELO.MIERCOLES.modelos.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapaUsuarioDTO {

    IMapaUsuarioDTO INSTANCE= Mappers.getMapper(IMapaUsuarioDTO.class);

    //Convirtiendo un modelo en DTO
    UsuarioDTO convertirADTO(Usuario usuario);

}
