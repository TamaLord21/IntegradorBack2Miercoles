package com.example.API.MODELO.MIERCOLES.modelos.mapas;

import com.example.API.MODELO.MIERCOLES.modelos.Asistencia;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.ResumenAsistenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface IMapaAsistenciaDTO {
    IMapaAsistenciaDTO INSTANCE = Mappers.getMapper(IMapaAsistenciaDTO.class);

    @Mapping(source = "fecha", target = "mes", qualifiedByName = "extraerMesDesdeFecha")
    @Mapping(source = "estudiante.usuario.id", target = "estudianteId")
    @Mapping(source = "estudiante.usuario.nombre", target = "nombreEstudiante")
    @Mapping(target = "totalPresentes", constant = "0")
    @Mapping(target = "totalAusentes", constant = "0")
    @Mapping(target = "resultado", constant = "0.0")
    ResumenAsistenciaDTO convertirADTO(Asistencia asistencia);

    List<ResumenAsistenciaDTO> convertirADTO(List<Asistencia> lista);

    @Named("extraerMesDesdeFecha")
    default String extraerMesDesdeFecha(LocalDate fecha) {
        if (fecha == null) return null;
        return fecha.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("es", "ES"))
                .toUpperCase();
    }
}
