package net.bmmv.parking.controller;

import jakarta.validation.Valid;
import net.bmmv.parking.excepcion.MetodoNoPermitidoExcepcion;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.RecargaDTO;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.service.IServiceComercio;
import net.bmmv.parking.service.IServiceRecarga;
import net.bmmv.parking.service.IServiceUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/recargas")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerRecarga {
    private static final Logger logger = LoggerFactory.getLogger(ControllerRecarga.class);

    @Autowired
    private IServiceRecarga serviceRecarga;
    @Autowired
    private IServiceComercio serviceComercio;
    @Autowired
    private IServiceUsuario serviceUsuario;

    @GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RecargaDTO>> obtenerRecargas() throws Exception {
        var recargas = serviceRecarga.listarTodas();
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas que listar");
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas que listar");
        }
        return new ResponseEntity<>(serviceRecarga.convertirARecargaDTO(recargas), HttpStatus.OK);
    }

    @GetMapping(value="/patente/{patente}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorPatente(@PathVariable String patente) throws Exception {
        var recargas = serviceRecarga.listarPorPatente(patente);
        var recargasDTO = new ArrayList<RecargaDTO>();

        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con la patente: " + patente);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con la patente: " + patente);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/dni/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorDniUsuario(@PathVariable Long dni) throws Exception {
        var recargas = serviceRecarga.listarPorUsuario(dni);
        var recargasDTO = new ArrayList<RecargaDTO>();
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con el DNI: " + dni);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el DNI: " + dni);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

    @GetMapping( value="/comercio/{cuit}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorComercio(@PathVariable Long cuit) throws Exception {
        logger.info(cuit.toString());
        Comercio comercio = serviceComercio.buscarComercioPorCuit(cuit);
        if(comercio == null){ throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el CUIT: " + cuit);}
        logger.info(comercio.getId_comercio().toString());
        var recargas = serviceRecarga.listarPorComercio(comercio.getId_comercio());

        var recargasDTO = new ArrayList<RecargaDTO>();
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con el CUIT: " + cuit);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el CUIT: " + cuit);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity guardar( @RequestBody Recarga recarga, BindingResult result) throws Exception{
        if (result.hasFieldErrors()){
            return validation(result);
        }
        logger.info(" ###### INICIANDO CONTROLADOR ##### ");
        //verificar y asignar el comercio
        Optional<Comercio> comercioOpt = Optional.of(serviceComercio.consultarComercio(recarga.getComercio().getCuit()));
        logger.info("ComercioOpt: "+comercioOpt.toString());

        comercioOpt.orElseThrow(() -> {
            logger.error("No se puede hacer la recarga");
            return new RecursoNoEncontradoExcepcion("No se permite hacer la recarga");
        });
        recarga.setComercio(comercioOpt.get());

        // Verificar y asignar el usuario
        Optional<Usuario> userOpt =  serviceUsuario.buscarUsuarioPorPatente(recarga.getPatente());
        logger.info("UserOpt: " + userOpt.toString());
        Usuario usuario = userOpt.orElseThrow(() -> {
            logger.error("No se puede hacer la recarga");
            return new RecursoNoEncontradoExcepcion("La patente no pertenece a ningún usuario");
        });
        // toma el usuario de la busqueda Optional positiva anterior y se lo asigna al atributo usuario
        //recarga.setUsuario(userOpt.get());

        recarga.setUsuario(usuario);

        // Asignar la fecha y hora actuales
        logger.info(LocalDateTime.now().toString());
        recarga.setFecha_hora(LocalDateTime.now());

        // Guardar la recarga y construye la URI de la respuesta
        Recarga recargaGuardada = serviceRecarga.guardar(recarga);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(recargaGuardada.getId_recarga()).toUri();

        logger.info("Datos de la recarga: " + recarga.toString());

        return ResponseEntity.created(location).body(recargaGuardada);

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}
