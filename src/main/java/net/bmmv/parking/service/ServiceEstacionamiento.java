package net.bmmv.parking.service;

import net.bmmv.parking.controller.ControllerEstacionamiento;
import net.bmmv.parking.controller.ControllerUsuario;
import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.model.EstacionamientoDTO;
import net.bmmv.parking.repository.RepositoryEstacionamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEstacionamiento implements IServiceEstacionamiento{

    @Autowired
    private RepositoryEstacionamiento repoEstacionamiento;

    @Override
    public List<Estacionamiento> ListarTodos() {
        return repoEstacionamiento.findAll();
    }

    @Override
    public Optional<List<Estacionamiento>> BuscarPorPatente(String patente) {
        return repoEstacionamiento.findAllByPatente(patente);
    }

    @Override
    public Optional<Estacionamiento> buscarPorPatenteyEstado(String patente, String estado) {
        return repoEstacionamiento.findEstacionamientoByPatenteAndEstado(patente, estado);
    }


    @Override
    public Estacionamiento guardarEstacionamiento(Estacionamiento estacionamiento) {
        return repoEstacionamiento.save(estacionamiento);
    }

    @Override
    public void inyectarLinkAlUsuario(List<Estacionamiento> todos) {
        for (Estacionamiento estacionamiento : todos) {
            Link usuarioLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ControllerUsuario.class)
                            .obtenerUsuarioPorId(estacionamiento.getUsuario().getDni())
            ).withRel("usuario");
            estacionamiento.add(usuarioLink);

        }
    }

    @Override
    public void inyectarLinkAlUsuario(EstacionamientoDTO dto) {
        Link link = WebMvcLinkBuilder.linkTo(ControllerEstacionamiento.class)
                .slash("/patente/" + dto.getPatente())
                .withRel("Patente");
        dto.add(link);
    }
    @Override
    public void inyectarLinkAlUsuario(Estacionamiento estacionamiento) {
        Link link = WebMvcLinkBuilder.linkTo(ControllerEstacionamiento.class)
                .slash("/patente/" + estacionamiento.getPatente())
                .withRel("Patente");
        estacionamiento.add(link);
    }

}
