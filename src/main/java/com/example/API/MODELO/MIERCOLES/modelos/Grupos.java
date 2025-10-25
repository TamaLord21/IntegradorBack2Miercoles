package com.example.API.MODELO.MIERCOLES.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;

    @Column(name = "semestre", nullable = false, unique = false)
    private Integer semestre;

    // 🔹 Relación con Materia (MUCHOS grupos → UNA materia)
    @ManyToOne
    @JoinColumn(name = "fk_materia", referencedColumnName = "id")
    @JsonBackReference(value = "relacionentremateriaygrupos")
    private Materia materia;

    // 🔹 Relación con Asistencia (UN grupo → MUCHAS asistencias)
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "relacionentregrupoyasistencia")
    private List<Asistencia> asistencias;

    // 🔹 Constructores
    public Grupos() {
    }

    public Grupos(Integer id, String nombre, Integer semestre, Materia materia) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.materia = materia;
    }

    // 🔹 Getters y Setters
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

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
}
