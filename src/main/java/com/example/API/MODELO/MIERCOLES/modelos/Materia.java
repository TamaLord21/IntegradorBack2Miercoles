package com.example.API.MODELO.MIERCOLES.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "creditos", nullable = false, unique = false)
    private Integer creditos;

    // Constructor vacío
    public Materia() {
    }

    // Constructor con parámetros
    public Materia(Integer id, String nombre, String codigo, Integer creditos) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    // Getters y Setters
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
}
