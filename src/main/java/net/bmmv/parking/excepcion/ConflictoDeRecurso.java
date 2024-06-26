package net.bmmv.parking.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictoDeRecurso extends RuntimeException{

    public ConflictoDeRecurso(String mensaje){
        super(mensaje);
    }
}