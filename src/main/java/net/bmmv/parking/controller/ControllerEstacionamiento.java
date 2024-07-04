package net.bmmv.parking.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import net.bmmv.parking.excepcion.ConflictoDeRecurso;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.model.EstacionamientoDTO;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.service.IServiceEstacionamiento;
import net.bmmv.parking.service.IServiceUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private IServiceUsuario serviceUsuario;

    static final Logger logger = LoggerFactory.getLogger(ControllerUsuario.class);

    @Operation(summary = "Permite listar los registros de estacionamiento")
    @GetMapping("/")
    public ResponseEntity<List<Estacionamiento>> obtenerEstacionamientos(){
        List<Estacionamiento> todos = serviceEstacionamiento.ListarTodos();
        serviceEstacionamiento.inyectarLinkAlUsuario(todos);
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @Operation(summary = "Permite listar estacionameintos por patente")
    @GetMapping("/patente/{patente}")
    public ResponseEntity<?> listarEstacionamientosPorPatente(@PathVariable String patente) {
        List<Estacionamiento> lista = serviceEstacionamiento.BuscarPorPatente(patente).orElseThrow();
        if (lista.isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("No se encuentran registros de estacionamiento con la patente: " + patente);
        }
        serviceEstacionamiento.inyectarLinkAlUsuario(lista);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @Operation(summary = "Permite Ocupar y liberar un estacionamiento dependiendo de su estado")
    @PostMapping("/ocupaylibera")
    public ResponseEntity<?> agregarRegistroEstacionamiento(@Valid @RequestBody EstacionamientoDTO dto, BindingResult result) throws Exception {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        String contrasenaRecibida = dto.getContrasena();
        String patenteRecibida = dto.getPatente();
        Optional<Usuario> userEstacionamiento = serviceUsuario.buscarUsuarioPorPatente(patenteRecibida);

        // Busca si existe un registro cargado para esta patente con estado "Ocupado"
        Optional<Estacionamiento> estacionamientoOcupado = serviceEstacionamiento.buscarPorPatenteyEstado(patenteRecibida, "Ocupado");

        // Si existe registro de la patente buscada con estado OCUPADO, se lo libera y se guarda hora de finalización
        if (estacionamientoOcupado.isPresent()) {
            Estacionamiento estacionamiento = estacionamientoOcupado.get();
            LocalDateTime horaFin = LocalDateTime.now();
            horaFin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            estacionamiento.setFecha_hora_fin(horaFin);
            estacionamiento.setEstadoLibre();
            estacionamiento.setId_estacionamiento(estacionamientoOcupado.get().getId_estacionamiento());
            // Link al listado de estacionamientos con esa patente
            serviceEstacionamiento.inyectarLinkAlUsuario(estacionamiento);
            logger.info("Registro de estacionamiento modificado para la patente " + patenteRecibida + " con estado LIBRE - " + horaFin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            return ResponseEntity.status(HttpStatus.OK).body(serviceEstacionamiento.guardarEstacionamiento(estacionamiento));
          // si no se encuentra registro estacionamiento de esa patente pero se verifica que es de un usuario activo
        } else if (userEstacionamiento.isPresent()) {
            if (userEstacionamiento.get().getContrasena().equals(contrasenaRecibida)) { //se compara la contraseña del usuario con la presentada en activacion o corte de estacionameinto
                Estacionamiento estacionamientoNuevo = new Estacionamiento();
                estacionamientoNuevo.setPatente(dto.getPatente());
                estacionamientoNuevo.setEstadoOcupado();
                estacionamientoNuevo.setFecha_hora_inicio(LocalDateTime.now());
                estacionamientoNuevo.setUsuario(userEstacionamiento.get());

                // Link al listado de estacionamientos con esa patente
                serviceEstacionamiento.inyectarLinkAlUsuario(dto);
                dto.setEstadoOcupado();
                dto.setContrasena("Contraseña aceptada");
                serviceEstacionamiento.guardarEstacionamiento(estacionamientoNuevo);
                logger.info("Registro de estacionamiento creado para la patente " + patenteRecibida + " con estado OCUPADO");
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            } else {
                logger.error("Contraseña incorrecta!");
                throw new ConflictoDeRecurso("Contraseña incorrecta");
            }
        } else {
            logger.error("No se encuentra usuario con la patente: " + patenteRecibida);
            throw new RecursoNoEncontradoExcepcion("No se encuentra usuario con la patente: " + patenteRecibida);
        }
    }



    @Hidden
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
