package com.example.API.MODELO.MIERCOLES.modelos.dtos;

public class ObservacionAsistenciaDTO {
    private Integer idAsistencia;
    private String observacion;

    public ObservacionAsistenciaDTO() {}

    public ObservacionAsistenciaDTO(Integer idAsistencia, String observacion) {
        this.idAsistencia = idAsistencia;
        this.observacion = observacion;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
