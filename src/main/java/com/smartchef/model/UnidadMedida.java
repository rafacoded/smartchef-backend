package com.smartchef.model;

public enum UnidadMedida {
    GRAMO("g"),
    MILILITRO("ml"),
    UNIDAD("unidad"),
    CUCHARADA("cda"),
    TAZA("taza");

    private final String simbolo;

    UnidadMedida(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
