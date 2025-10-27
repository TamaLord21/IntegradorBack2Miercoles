package com.example.API.MODELO.MIERCOLES.dtos;

public class GruposDTO {
    private Integer id;
    private String nombre;
    private Integer semestre;

    public GruposDTO(Integer id, String nombre, Integer semestre) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getSemestre() {
        return semestre;
    }
}
