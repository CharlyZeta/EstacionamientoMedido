package net.bmmv.parking.controller;

import jakarta.validation.Valid;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.service.IServiceComercio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comercios")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerComercio {

    private static final Logger logger = LoggerFactory.getLogger(ControllerComercio.class);

    @Autowired
    private IServiceComercio serviceComercio;

    @PostMapping("/")
    public ResponseEntity<?> agregarComercio(@Valid @RequestBody Comercio comercio, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceComercio.guardarComercio(comercio));
    }


    @PutMapping("/{Cuit}")
    public ResponseEntity<?> actualizarComercio(@Valid @RequestBody Comercio comercioRecibido, BindingResult result,
                                                @PathVariable Long Cuit) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Comercio comercio = serviceComercio.buscarComercioPorCuit(Cuit);
        if (comercio == null)
            throw new RecursoNoEncontradoExcepcion("El Cuit recibido no existe: " + Cuit);

        return ResponseEntity.status(HttpStatus.OK).body(serviceComercio.actualizarComercioPorCuit(comercio, comercioRecibido));

    }

    @PutMapping("/baja/{Cuit}")
    public ResponseEntity<?> bajaComercio(@PathVariable Long Cuit) {
        Comercio comercio = serviceComercio.buscarComercioPorCuit(Cuit);
        if (comercio == null) {
            throw new RecursoNoEncontradoExcepcion("El Cuit recibido no existe: " + Cuit);
        }else{
            if(comercio.getEstado().equals("Suspendido")){
                throw new RecursoNoEncontradoExcepcion("El Cuit recibido ya fue dado de baja: " + Cuit);
            }
        }

        serviceComercio.bajaComercio(comercio);

        return ResponseEntity.ok(comercio);

    }

    @GetMapping("/")
    public ResponseEntity<?> consultaComercios() {
        List<Comercio> listaComercios = serviceComercio.listarComercios();
        if (listaComercios.isEmpty())
            throw new RecursoNoEncontradoExcepcion("No existen comercios guardados.");

        return ResponseEntity.ok(serviceComercio.listarComercios());

    }

    @GetMapping("/{Cuit}")
    public ResponseEntity<?> consultaComercio(@PathVariable Long Cuit) {
        Comercio comercio = serviceComercio.buscarComercioPorCuit(Cuit);

        //HEATOAS INICIO

        Link recargaLink = WebMvcLinkBuilder.linkTo(ControllerRecarga.class)
                .slash("recargarSaldo").withRel("Cargar saldo");

        comercio.add(recargaLink);

        //HEATOAS FIN

        if (comercio == null)
            throw new RecursoNoEncontradoExcepcion("El Cuit recibido no existe: " + Cuit);

        return ResponseEntity.ok(comercio);

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
