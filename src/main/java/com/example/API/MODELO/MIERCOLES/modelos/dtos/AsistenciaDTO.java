package com.example.API.MODELO.MIERCOLES.modelos.dtos;

import java.time.LocalDate;

public class AsistenciaDTO {

    private LocalDate fecha;
    private String observacion;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(LocalDate fecha, String observacion) {
        this.fecha = fecha;
        this.observacion = observacion;
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

    public  LocalDate findByFecha (){ return fecha; }
}
