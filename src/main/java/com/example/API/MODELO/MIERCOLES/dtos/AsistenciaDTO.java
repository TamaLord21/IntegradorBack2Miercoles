package com.example.API.MODELO.MIERCOLES.dtos;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;

import java.time.LocalDate;

public class AsistenciaDTO {

    private LocalDate fecha;
    private String observacion;
    private EstadosAsistencia estado;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(LocalDate fecha, String observacion, EstadosAsistencia estado) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public EstadosAsistencia getEstado() {
        return estado;
    }
    public void  getEstado(EstadosAsistencia estado) {
        this.estado = estado;
    }

    public  LocalDate findByFecha (){ return fecha; }
}
