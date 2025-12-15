package com.smartchef.exception;

public class OperacionNoPermitidaException extends SmartChefException {

    private final String detalle;

    public OperacionNoPermitidaException(String mensaje, String detalle) {
        super(mensaje);
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }
}
