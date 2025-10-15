package com.example.API.MODELO.MIERCOLES.modelos.dtos;

import com.example.API.MODELO.MIERCOLES.ayudas.RolesUsuario;

import java.time.LocalDate;

public class EstudianteEspecialDTO {

    private String nombre;
    private String contraseña;
    private RolesUsuario rol;
    private Double promedio;
    private LocalDate fechaNacimiento;

    public EstudianteEspecialDTO() {
    }

    public EstudianteEspecialDTO(String nombre, String contraseña, RolesUsuario rol, Double promedio, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        this.promedio = promedio;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public RolesUsuario getRol() {
        return rol;
    }

    public void setRol(RolesUsuario rol) {
        this.rol = rol;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
