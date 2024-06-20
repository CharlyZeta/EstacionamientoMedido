package net.bmmv.parking.service;

import net.bmmv.parking.model.Estacionamiento;

import java.util.List;
import java.util.Optional;

public interface IServiceEstacionamiento {

    public List<Estacionamiento> findAll();
    public Optional<Estacionamiento> getById(Long id);

}
