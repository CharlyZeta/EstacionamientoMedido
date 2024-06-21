package net.bmmv.parking.controller;

import jakarta.validation.Valid;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.service.IServiceEstacionamiento;
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
import java.util.Optional;

@RestController
@RequestMapping("/estacionamiento")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerEstacionamiento {

    @Autowired
    private IServiceEstacionamiento serviceEstacionamiento;

    @GetMapping("/")
    public ResponseEntity<List<Estacionamiento>> obtenerEstacionamientos(){
        List<Estacionamiento> todos = serviceEstacionamiento.ListarTodos();
//        if(todos.isEmpty())
//            throw new RecursoNoEncontradoExcepcion("No se encontraron registros");
//        //todos.forEach(e -> logger.info(e.toString()));
//        for(Estacionamiento est : todos){
//            String patente = est.getPatente_vehiculo();
//            // Crea un link a si mismo
//            Link selfLink = WebMvcLinkBuilder.linkTo(ControllerEstacionamiento.class)
//                    .slash(est.getPatente_vehiculo())
//                    .withSelfRel();
//            est.add(selfLink);
//            Link parking = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerEstacionamiento.class)
//                        .getById(usuarioDTO.getPatente()) implementar método en controlador ControllerEstacionamiento
//                        .withRel("estacionemiento");
//        }
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }
    @PostMapping("/")
    public ResponseEntity<?> agregarRegistroEstacionamiento(@Valid @RequestBody Estacionamiento estacionamiento, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceEstacionamiento.guardarEstacionamiento(estacionamiento));
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
