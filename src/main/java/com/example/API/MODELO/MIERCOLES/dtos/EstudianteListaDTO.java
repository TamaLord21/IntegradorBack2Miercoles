package com.example.API.MODELO.MIERCOLES.dtos;

public class EstudianteListaDTO {
    private Integer idEstudiante;
    private String nombre;
    private String correo;

    public EstudianteListaDTO(Integer idEstudiante, String nombre, String correo) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
