package com.smartchef.exception;

public class EliminarNoExistenteException extends RuntimeException {

    public EliminarNoExistenteException(String mensaje){
        super(mensaje);
    }

}