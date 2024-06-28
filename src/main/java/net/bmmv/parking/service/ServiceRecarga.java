package net.bmmv.parking.service;

import net.bmmv.parking.controller.ControllerComercio;
import net.bmmv.parking.controller.ControllerRecarga;
import net.bmmv.parking.controller.ControllerUsuario;
import net.bmmv.parking.excepcion.ErrorInternoDelServidorExcepcion;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.*;
import net.bmmv.parking.repository.RepositoryComercio;
import net.bmmv.parking.repository.RepositoryRecarga;
import net.bmmv.parking.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ServiceRecarga implements IServiceRecarga  {

    @Autowired
    private RepositoryRecarga repositoryRecarga;
    @Autowired
    private RepositoryComercio repositoryComercio;
    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Override
    public List<Recarga> listarTodas() {
        //return repositoryRecarga.findAll(Sort.by(Sort.Direction.ASC, "fecha_Hora"));
        return repositoryRecarga.findAll();
    }

    @Override
    public Recarga guardar(Recarga recarga) throws Exception  {
        return repositoryRecarga.save(recarga);
    }


    public RecargaDTO devuelveRecargaDTO(Recarga recarga) {
        RecargaDTO dto = new RecargaDTO(recarga);
        dto.setId_recarga(recarga.getId_recarga());
        dto.setPatente(recarga.getPatente());
        dto.setImporte(recarga.getImporte());
        dto.setFecha_hora(recarga.getFecha_hora());
        dto.setUsuario(recarga.getUsuario());
        dto.setComercio(recarga.getComercio());

        // link: Link al servicio que permite consultar los datos del usuario
        Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerUsuario.class)
                        .obtenerUsuarioPorId(dto.getUsuario().getDni())).withRel("Usuario");
        dto.add(userLink);

        // link: Link al servicio que permite consultar los datos del comercio
        Link comercioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerComercio.class)
                .consultaComercio(dto.getComercio().getCuit()))
                .withRel("Comercio");
        dto.add(comercioLink);

        return dto;
    }

    @Override
    public List<Recarga> listarPorPatente(String patente) {
        return repositoryRecarga.findAllByPatente(patente);
    }

    @Override
    public List<Recarga> listarPorUsuario(Long dniUsuario) {
        return repositoryRecarga.findAllByUsuarioDni(dniUsuario);
    }

    @Override
    public List<Recarga> listarPorComercio(Long idComercio) {
        return repositoryRecarga.findAllById(Collections.singleton(idComercio));

    }

    public Recarga buscarRecargaPorId(Long id){
        return repositoryRecarga.findById(id).orElseThrow();
    }

    public ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    public List<RecargaDTO> convertirARecargaDTO(List<Recarga> recargas){
        var recargasDTO = new ArrayList<RecargaDTO>();
        for(Recarga recarga : recargas){
            recargasDTO.add(devuelveRecargaDTO(recarga));
        }
        return recargasDTO;
    }

    public Recarga inyectarLinkUsuarioYComercio(Recarga recarga, Optional<Usuario> usuarioOpt, Long idComercioRecibido) {
        // link: Link al servicio que permite consultar los datos del usuario
        Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerUsuario.class)
                .obtenerUsuarioPorId(usuarioOpt.get().getDni())).withRel("Usuario: ");
        recarga.add(userLink);

        // link: Link al servicio que permite consultar los datos del comercio
        Link comercioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControllerComercio.class)
                        .consultaComercio(idComercioRecibido))
                .withRel("Comercio");
        recarga.add(comercioLink);



        return recarga;
    }
}
