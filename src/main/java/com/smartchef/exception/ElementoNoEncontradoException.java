package com.smartchef.exception;

public class ElementoNoEncontradoException extends SmartChefException {

    private final String detalle;

    public ElementoNoEncontradoException(String mensaje, String detalle) {
        super(mensaje);
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }
}
