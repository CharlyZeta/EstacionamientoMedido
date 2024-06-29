package net.bmmv.parking.service;

import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.model.EstacionamientoDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceEstacionamiento {

    public List<Estacionamiento> ListarTodos();

    public Optional<List<Estacionamiento>> BuscarPorPatente(String patente);

    public Optional<Estacionamiento> buscarPorPatenteyEstado(String patente, String estado);
    public Estacionamiento guardarEstacionamiento(Estacionamiento estacionamiento);

    void inyectarLinkAlUsuario(List<Estacionamiento> todos);

    void inyectarLinkAlUsuario(EstacionamientoDTO dto);

    void inyectarLinkAlUsuario(Estacionamiento estacionamiento);
}
