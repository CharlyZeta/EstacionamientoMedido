package net.bmmv.parking.service;

import net.bmmv.parking.controller.ControllerUsuario;
import net.bmmv.parking.model.*;
import net.bmmv.parking.repository.RepositoryComercio;
import net.bmmv.parking.repository.RepositoryRecarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecarga implements IServiceRecarga  {

    @Autowired
    private RepositoryRecarga repositoryRecarga;
    @Autowired
    private RepositoryComercio repositoryComercio;

    @Override
    public List<Recarga> listarTodas() {
        List<Recarga> listaDeRecargas = repositoryRecarga.findAll();
        //return repositoryRecarga.findAll(Sort.by(Sort.Direction.ASC, "fecha_Hora"));
        return listaDeRecargas;
    }

    @Override
    public Recarga guardar(Recarga recarga) {
        return repositoryRecarga.save(recarga);
    }


    public RecargaDTO devuelveRecargaDTO(Recarga recarga) {
        RecargaDTO dto = new RecargaDTO(recarga);
        dto.setId_recarga(recarga.getId_recarga());
        dto.setPatente(recarga.getPatente());
        dto.setImporte(recarga.getImporte());
        dto.setFecha_hora(recarga.getFecha_hora());
        dto.setUsuario(recarga.getUsuario());
        //dto.setComercio(recarga.getComercio());

        Link userLink = WebMvcLinkBuilder.linkTo(ControllerUsuario.class)
                .slash(dto.getUsuario()).withSelfRel();

//        Link selfLink = WebMvcLinkBuilder.linkTo(ControllerRecarga.class)
//            .slash(dto.get).withRel("Recarga");

        //Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada a la persona
//            Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
//                            .getById(pojo.getCiudad().getId()))
//                    .withRel("ciudad");

//        dto.add(selfLink);
        dto.add(userLink);
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




}
