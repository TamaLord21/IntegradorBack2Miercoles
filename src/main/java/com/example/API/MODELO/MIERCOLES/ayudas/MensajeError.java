package com.example.API.MODELO.MIERCOLES.ayudas;

public enum MensajeError {

    // ====== USUARIOS ======
    ERROR_GENERAL_USUARIO("Upps fallamos al intentar procesar el API de usuarios"),
    ERROR_USUARIO_NO_ENCONTRADO("El usuario que buscas no está en BD"),

    // ====== ASISTENCIAS ======
    ERROR_GENERAL_ASISTENCIA("Upps fallamos al intentar procesar el API de asistencias"),
    ERROR_ASISTENCIA_NO_ENCONTRADA("La asistencia que buscas no está en BD"),
    ERROR_ASISTENCIA_EXISTENTE("Ya existe una asistencia registrada con esos datos"),
    ERROR_ASISTENCIA_FECHA_INVALIDA("La fecha de asistencia no es válida");

    private final String descripcion;

    MensajeError(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
