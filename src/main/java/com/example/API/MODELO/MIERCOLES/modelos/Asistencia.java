package com.example.API.MODELO.MIERCOLES.modelos;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Fecha de la asistencia
    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = true)
    private String observacion;

    // Estado de la asistencia resente o ausente
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadosAsistencia estado;

    // Relación con la entidad Estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "relacionentreestudianteyasistencia")
    private Estudiante estudiante;


    public Asistencia() {
    }

    public Asistencia(LocalDate fecha, String observacion, EstadosAsistencia estado, Estudiante estudiante) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
        this.estudiante = estudiante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstadosAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadosAsistencia estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

}

