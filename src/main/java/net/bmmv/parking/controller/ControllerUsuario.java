package net.bmmv.parking.controller;

import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.service.IServiceUsuario;
import org.apache.el.stream.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerUsuario {

    private static final Logger logger = LoggerFactory.getLogger(ControllerUsuario.class);

    @Autowired
    private IServiceUsuario serviceUsuario;

    @GetMapping("/")
    public List<Usuario> obtenerUsuarios(){
        var usuarios = serviceUsuario.ListarUsuarios();
        usuarios.forEach(e -> logger.info(e.toString()));
        return usuarios;
    }
    @GetMapping("/{Dni}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long Dni){
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(Dni);
        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el DNI: " + Dni);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/")
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario a agregar: " + usuario );
        return serviceUsuario.guardarUsuario(usuario);
    }

    @PutMapping("/{Dni}")
    public ResponseEntity<Usuario> actualizarEmpleado(@PathVariable Long Dni,@RequestBody Usuario usuarioRecibido){
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(Dni);
        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("El DNI recibido no existe: " + Dni);

        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido(usuarioRecibido.getApellido());
        usuario.setFecha_nacimiento(usuarioRecibido.getFecha_nacimiento());
        usuario.setDomicilio(usuarioRecibido.getDomicilio());
        usuario.setEmail(usuarioRecibido.getEmail());
        usuario.setContrasena(usuario.getContrasena());
        usuario.setPatente(usuarioRecibido.getPatente());
        serviceUsuario.guardarUsuario(usuario);
        logger.info("Se le aplicaron cambios al usuario "
                + usuario.getApellido() + " "
                + usuario.getApellido() + " DNI: "
                + usuario.getDni() );
        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{dni}")
    public ResponseEntity<String> eliminar(@PathVariable("dni") Long dni)
    {
        Usuario usuario = serviceUsuario.buscarUsuarioPorDni(dni);
        if(usuario == null)
            throw new RecursoNoEncontradoExcepcion("El DNI recibido no existe: " + dni);
        serviceUsuario.eliminarUsuario(usuario);
        logger.info("Se le eliminó al usuario "
                + usuario.getApellido() + " "
                + usuario.getApellido() + " DNI: "
                + usuario.getDni() );
        return ResponseEntity.ok("Usuario con DNI " + dni + " eliminado correctamente.");
    }




}
