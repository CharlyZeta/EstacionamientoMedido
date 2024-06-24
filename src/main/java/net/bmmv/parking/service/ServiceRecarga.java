package net.bmmv.parking.service;

import net.bmmv.parking.controller.ControllerComercio;
import net.bmmv.parking.controller.ControllerRecarga;
import net.bmmv.parking.controller.ControllerUsuario;
import net.bmmv.parking.model.*;
import net.bmmv.parking.repository.RepositoryComercio;
import net.bmmv.parking.repository.RepositoryRecarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Recarga guardar(Recarga recarga) throws Exception {
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



    public List<RecargaDTO> convertirARecargaDTO(List<Recarga> recargas){
        var recargasDTO = new ArrayList<RecargaDTO>();
        for(Recarga recarga : recargas){
            recargasDTO.add(devuelveRecargaDTO(recarga));
        }
        return recargasDTO;
    }

}
