package com.example.API.MODELO.MIERCOLES.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaUsuarioDTO {

    IMapaUsuarioDTO INSTANCE= Mappers.getMapper(IMapaUsuarioDTO.class);

    //Convirtiendo un modelo en DTO


    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "rol", target = "rol")
    UsuarioDTO convertirADTO(Usuario usuario);

    List<UsuarioDTO> convertirListaDTO(List<Usuario> lista);

}
