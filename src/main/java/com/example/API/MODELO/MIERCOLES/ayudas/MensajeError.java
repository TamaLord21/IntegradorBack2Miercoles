package com.example.API.MODELO.MIERCOLES.ayudas;

public enum MensajeError {

    ERROR_GENERAL_USUARIO("Upps fallamos al intentar procesar el API"),
    ERROR_USUARIO_NO_ENCONTRADO("El usuario que buscas no está en BD")
    ;

    private final String descripcion;

    MensajeError(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
