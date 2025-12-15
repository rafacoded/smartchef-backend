package com.smartchef.exception;

public record ApiError(
        String codigo,
        String mensaje,
        Object detalle
) {}
