package net.bmmv.parking.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.*;
import jakarta.validation.Valid;
import net.bmmv.parking.excepcion.MetodoNoPermitidoExcepcion;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.model.UsuarioDTO;
import net.bmmv.parking.service.IServiceUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.core.WebHandler.linkTo;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerUsuario {

    static final Logger logger = LoggerFactory.getLogger(ControllerUsuario.class);

    @Autowired
    private IServiceUsuario serviceUsuario;

    @GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() throws Exception{
        List<Usuario> usuarios = serviceUsuario.ListarUsuarios();
        usuarios.forEach(e -> logger.info(e.toString()));
        if(usuarios.isEmpty()){
            logger.error("No se encuentran usuarios que listar");
            throw new RecursoNoEncontradoExcepcion("No se encuentran usuarios que listar");
        }

        return new ResponseEntity<>(serviceUsuario.convertirAUsuarioDTO(usuarios), HttpStatus.OK);
    }




    @GetMapping("/{Dni}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long Dni){
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(Dni);


        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el DNI: " + Dni);
        String patente = usuario.getPatente();

        return ResponseEntity.status(HttpStatus.OK).body(serviceUsuario.devuelveUsuarioDTO(usuario));
    }



    @PostMapping("/")
    public ResponseEntity<?> agregarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.guardarUsuario(usuario));
    }

    @PutMapping("/{Dni}")
    public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody Usuario usuarioRecibido, BindingResult result, @PathVariable Long Dni){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(Dni);
        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("El DNI recibido no existe: " + Dni);

        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido(usuarioRecibido.getApellido());
        usuario.setFecha_nacimiento(usuarioRecibido.getFecha_nacimiento());
        usuario.setDomicilio(usuarioRecibido.getDomicilio());
        usuario.setEmail(usuarioRecibido.getEmail());
        usuario.setFecha_nacimiento(usuarioRecibido.getFecha_nacimiento());
        usuario.setContrasena(usuarioRecibido.getContrasena());
        usuario.setPatente(usuarioRecibido.getPatente());
        serviceUsuario.guardarUsuario(usuario);
        logger.info("Se le aplicaron cambios al usuario "
                + usuario.getNombre() + " "
                + usuario.getApellido() + " DNI: "
                + usuario.getDni() );
        return ResponseEntity.ok(usuario);

    }
    @PutMapping("/")
    public ResponseEntity<?> actualizarTodo(@PathVariable Optional<Long> dni){
        logger.error("Método no permitido para esta operación");
        throw new MetodoNoPermitidoExcepcion("Método no permitido para esta operación");
    }



    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminar(@PathVariable("dni") Long dni) {
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(dni);
        if(usuario == null) {
            logger.error("\"El DNI recibido no existe: \" + dni");
            throw new RecursoNoEncontradoExcepcion("El DNI recibido no existe: " + dni);
        }
        serviceUsuario.eliminarUsuario(usuario);
        logger.info("Se le eliminó al usuario "
                + usuario.getNombre() + " "
                + usuario.getApellido() + " DNI: "
                + usuario.getDni() );
        return ResponseEntity.ok("Usuario con DNI " + dni + " eliminado correctamente.");
    }
    @DeleteMapping("/")
    public ResponseEntity<?> eliminarTodo(@PathVariable Optional<Long> dni){
            logger.error("Método no permitido para esta operación");
            throw new MetodoNoPermitidoExcepcion("Método no permitido para esta operación");

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
