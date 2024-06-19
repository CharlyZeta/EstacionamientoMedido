package net.bmmv.parking.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoDelServidorExcepcion extends RuntimeException{

    public ErrorInternoDelServidorExcepcion(String mensaje){
        super(mensaje);
    }
}