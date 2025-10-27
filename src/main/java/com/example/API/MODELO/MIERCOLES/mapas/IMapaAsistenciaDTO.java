package com.example.API.MODELO.MIERCOLES.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.dtos.AsistenciaDTO;
import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaAsistenciaDTO {

    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstado")
    AsistenciaDTO convertirADTO(Asistencia asistencia);

    List<AsistenciaDTO> convertirListaDTO(List<Asistencia> lista);

    @Named("mapEstado")
    default EstadosAsistencia mapEstado(Object estado) {
        if (estado == null) return null;
        if (estado instanceof EstadosAsistencia) {
            return (EstadosAsistencia) estado;
        }
        // Si viene como string, convierte al enum (ignorando mayúsculas/minúsculas)
        return EstadosAsistencia.valueOf(estado.toString().trim().toUpperCase());
    }
}
