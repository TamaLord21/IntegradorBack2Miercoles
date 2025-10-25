package com.example.API.MODELO.MIERCOLES.modelos;

import com.example.API.MODELO.MIERCOLES.ayudas.EstadosAsistencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;

@Entity
@Table(name="asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false, unique = false)
    private LocalDate fecha;

    @Column(name = "observacion", nullable = true, unique = false)
    private String observacion;

    @Column(name="estado", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private EstadosAsistencia estado;


    //CREANDO UNA RELACION DE MUCHOS A UNO
    //2. COMO ME RELACIONO CON 1 SOLO ELEMENTO DE LA OTRA TABLA CREO UNA VARIABLE INDIVIDUAL
    @ManyToOne
    //3. Construyo la relacion entre las tablas (Defino la FK)
    @JoinColumn(name = "fk_estudiante",referencedColumnName = "id")
    @JsonBackReference(value="relacionentreestudianteyasistencia")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "fk_grupo", referencedColumnName = "id")
    @JsonBackReference(value = "relacionentregrupoyasistencia")
    private Grupos grupo;


    public Asistencia() {
    }

    public Asistencia(Integer id, LocalDate fecha, String observacion, EstadosAsistencia estado) {
        this.id = id;
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
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

    public Estudiante getEstudiante() { return estudiante; }

    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Grupos getGrupo() { return grupo; }

    public void setGrupo(Grupos grupo) { this.grupo = grupo; }
}
