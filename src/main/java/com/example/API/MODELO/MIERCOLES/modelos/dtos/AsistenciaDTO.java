package com.example.API.MODELO.MIERCOLES.modelos.dtos;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AsistenciaDTO {

    @NotNull(message = "La fecha no puede ser nula socio")
    private LocalDate fecha;

    @NotNull(message = "El estado no puede ser nulo socio")
    private EstadosAsistencia estado;

    @Size(max = 200, message = "La observación no puede superar los 200 caracteres, sea más breve")
    private String observacion;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(LocalDate fecha, EstadosAsistencia estado, String observacion) {
        this.fecha = fecha;
        this.estado = estado;
        this.observacion = observacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadosAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadosAsistencia estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

