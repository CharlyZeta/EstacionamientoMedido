package net.bmmv.parking.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MetodoNoPermitidoExcepcion extends RuntimeException{

    public MetodoNoPermitidoExcepcion(String mensaje){
        super(mensaje);
    }
}
