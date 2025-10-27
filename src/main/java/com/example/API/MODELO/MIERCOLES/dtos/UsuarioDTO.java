package com.example.API.MODELO.MIERCOLES.dtos;

import com.example.API.MODELO.MIERCOLES.ayudas.RolesUsuario;

public class UsuarioDTO {

    private String nombre;
    private String correo;
    private RolesUsuario rol;
    private String contraseña;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String correo, RolesUsuario rol, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
