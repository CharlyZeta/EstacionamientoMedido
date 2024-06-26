package net.bmmv.parking.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<?> handleRecursoNoEncontradoExcepcion(RecursoNoEncontradoExcepcion ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MetodoNoPermitidoExcepcion.class)
    public ResponseEntity<?> MetodoNoPermitidoExcepcion(MetodoNoPermitidoExcepcion ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ErrorInternoDelServidorExcepcion.class)
    public ResponseEntity<?> ErrorInternoDelServidorExcepcion(ErrorInternoDelServidorExcepcion ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConflictoDeRecurso.class)
    public ResponseEntity<?> ConflictoDeRecurso(ConflictoDeRecurso ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
