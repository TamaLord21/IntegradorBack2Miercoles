package com.example.API.MODELO.MIERCOLES.modelos.dtos;

import com.example.API.MODELO.MIERCOLES.ayudas.RolesUsuario;

public class UsuarioDTO {

    private String nombre;
    private String correo;
    private RolesUsuario rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String correo, RolesUsuario rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
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

    public RolesUsuario getRol() {
        return rol;
    }

    public void setRol(RolesUsuario rol) {
        this.rol = rol;
    }
}
