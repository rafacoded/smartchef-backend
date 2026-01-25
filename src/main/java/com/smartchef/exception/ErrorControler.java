package com.smartchef.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ErrorControler {


    // 404 - elemento no encontrado
    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<ApiError> handleNotFound(ElementoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        "NO_ENCONTRADO",
                        ex.getMessage(),
                        null
                ));
    }

    // 400 - relación inválida
    @ExceptionHandler(RelacionInvalidaException.class)
    public ResponseEntity<ApiError> handleRelacionInvalida(RelacionInvalidaException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiError(
                        "RELACION_INVALIDA",
                        ex.getMessage(),
                        null
                ));
    }

    // 400 - enum mal pasado (filtros)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleEnumError(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiError(
                        "VALOR_NO_VALIDO",
                        "Valor no válido para el parámetro '" + ex.getName() + "'",
                        ex.getValue()
                ));
    }

    // 400 - validaciones @NotNull, @NotBlank, etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Datos inválidos");

        return ResponseEntity.badRequest()
                .body(new ApiError(
                        "VALIDACION_ERROR",
                        mensaje,
                        null
                ));
    }

    // 500 - fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ex.printStackTrace(); // 👈 CLAVE
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(
                        "ERROR_INTERNO",
                        "Ha ocurrido un error inesperado",
                        null
                ));
    }

    @ExceptionHandler(OperacionNoPermitidaException.class)
    public ResponseEntity<ApiError> handleConflict(OperacionNoPermitidaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        "CONFLICTO",
                        ex.getMessage(),
                        ex.getDetalle()
                ));
    }

}
