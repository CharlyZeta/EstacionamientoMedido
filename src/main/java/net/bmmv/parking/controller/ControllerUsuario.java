package net.bmmv.parking.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private static final Logger logger = LoggerFactory.getLogger(ControllerUsuario.class);

    @Autowired
    private IServiceUsuario serviceUsuario;
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        var usuarios = serviceUsuario.ListarUsuarios();
        usuarios.forEach(e -> logger.info(e.toString()));
        for(Usuario user : usuarios){
            Long dni = user.getDni();
            // Crea un link a si mismo
            Link selfLink = WebMvcLinkBuilder.linkTo(ControllerUsuario.class)
                .slash(user.getDni())
                .withSelfRel();
            user.add(selfLink);
//            Link parking = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerEstacionamiento.class)
//                        .getById(usuarioDTO.getPatente()) implementar método en controlador ControllerEstacionamiento
//                        .withRel("estacionemiento");
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }




    @GetMapping("/{Dni}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long Dni){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(Dni);

        usuarioDTO.setDni(usuario.getDni());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setDomicilio(usuario.getDomicilio());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPatente(usuario.getPatente());
        usuarioDTO.setSaldo_cuenta(usuario.getSaldo_cuenta());

        //usuarioDTO.setEstacionamiento();
        Estacionamiento estacionamiento = new Estacionamiento();
        // implementar método en servicioEstacioanmeinto para buscar un registro de estacionamiento OCUPADO
        // if( patente_ocupa_estacionamiento )
        //        String estado = "OCUPADO";


        Link selfLink = WebMvcLinkBuilder.linkTo(ControllerUsuario.class)
                .slash(obtenerUsuarios()).withRel("Lista de usuarios");
//        //Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada a la persona
//        Link parking = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerEstacionamiento.class)
////                        .getById(usuarioDTO.getPatente()) implementar método en controlador ControllerEstacionamiento
//                        .withRel("estacionemiento");
        usuarioDTO.add(selfLink);
//        usuarioDTO.add(parking);

        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el DNI: " + Dni);
        String patente = usuario.getPatente();

//        usuario
//                .add(Link.of("/estacionamiento/estado/{patente}"));

//        return ResponseEntity.status(HttpStatus.OK).body(serviceUsuario.buscarUsuarioPorDni(Dni));

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
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
        usuario.setContrasena(usuarioRecibido.getContrasena());
        usuario.setPatente(usuarioRecibido.getPatente());
        serviceUsuario.guardarUsuario(usuario);
        logger.info("Se le aplicaron cambios al usuario "
                + usuario.getApellido() + " "
                + usuario.getApellido() + " DNI: "
                + usuario.getDni() );
        return ResponseEntity.ok(usuario);

    }
    @PutMapping("/")
    public ResponseEntity<?> actualizarTodo(@PathVariable Optional<Long> dni){
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
}
