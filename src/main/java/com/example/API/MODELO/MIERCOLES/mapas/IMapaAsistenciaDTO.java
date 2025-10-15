package com.example.API.MODELO.MIERCOLES.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.dtos.AsistenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaAsistenciaDTO {

    IMapaAsistenciaDTO INSTANCE = Mappers.getMapper(IMapaAsistenciaDTO.class);

    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "observacion", target = "observacion")
    AsistenciaDTO convertirADTO(Asistencia asistencia);

    List<AsistenciaDTO> convertirListaDTO(List<Asistencia> lista);
}