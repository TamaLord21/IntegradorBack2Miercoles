package com.example.API.MODELO.MIERCOLES.modelos.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.AsistenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface IMapaRegistroAsistenciaDTO {

    IMapaRegistroAsistenciaDTO INSTANCE = Mappers.getMapper(IMapaRegistroAsistenciaDTO.class);

    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "observacion", target = "observacion")
    @Mapping(source = "estado", target = "estado")
    AsistenciaDTO convertirResgistroAsistenciaDTO(Asistencia asistencia);

    List<AsistenciaDTO>convertirRegistroAsistenciaDTO(List<Asistencia>lista);
}
