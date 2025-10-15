package com.example.API.MODELO.MIERCOLES.modelos;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosUsuario;
import com.example.API.MODELO.MIERCOLES.ayudas.RolesUsuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = false, length = 50)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true, length = 80)
    private String correo;

    @Column(name = "contraseña", nullable = false, unique = false, length = 10)
    private String contraseña;

    @Column(name = "estado", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private EstadosUsuario estado;

    @Column(name = "rol", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private RolesUsuario rol;

    //DESPUES DE LOS ATRIBUTOS Y ANTES DEL CONSTRUCTOR DEFINO LAS RELACIONES
    //Definiendo una relacion UNO A UNO
    //1. Para representar en java una relacion de 1 con otra tabla creo una variable
    //de esa tabla con la que me voy a relacionar

    //2. Identifico el lado principal o manejador de la relacion(La tabla que tiene la FK)
    //4. Configuro la relacion en el lado NO DOMINANTE

    @OneToOne(mappedBy = "usuario")
    @JsonBackReference(value = "relacionentreusuarioyestudiante")
    private Estudiante estudiante;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String correo, String contraseña, EstadosUsuario estado, RolesUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public EstadosUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadosUsuario estado) {
        this.estado = estado;
    }

    public RolesUsuario getRol() {
        return rol;
    }

    public void setRol(RolesUsuario rol) {
        this.rol = rol;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
