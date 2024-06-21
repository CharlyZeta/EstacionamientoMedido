package net.bmmv.parking.service;

import net.bmmv.parking.model.Estacionamiento;

import java.util.List;
import java.util.Optional;

public interface IServiceEstacionamiento {

    public List<Estacionamiento> ListarTodos();

    //public Optional<Estacionamiento> BuscarPorPatente(String patente);
    public Estacionamiento guardarEstacionamiento(Estacionamiento estacionamiento);


}
