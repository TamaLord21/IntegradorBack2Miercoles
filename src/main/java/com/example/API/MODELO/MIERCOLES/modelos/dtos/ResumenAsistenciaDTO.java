package com.example.API.MODELO.MIERCOLES.modelos.dtos;

public class ResumenAsistenciaDTO {
    private String mes;
    private Integer totalPresentes;
    private Integer totalAusentes;
    private Integer estudianteId;
    private String nombreEstudiante;

    public ResumenAsistenciaDTO(String mes, Integer totalPresentes, Integer totalAusentes,
                                Integer estudianteId, String nombreEstudiante) {
        this.mes = mes;
        this.totalPresentes = totalPresentes;
        this.totalAusentes = totalAusentes;
        this.estudianteId = estudianteId;
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getTotalPresentes() {
        return totalPresentes;
    }

    public void setTotalPresentes(Integer totalPresentes) {
        this.totalPresentes = totalPresentes;
    }

    public Integer getTotalAusentes() {
        return totalAusentes;
    }

    public void setTotalAusentes(Integer totalAusentes) {
        this.totalAusentes = totalAusentes;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
}
