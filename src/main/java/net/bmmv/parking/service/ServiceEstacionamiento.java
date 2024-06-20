package net.bmmv.parking.service;

import net.bmmv.parking.model.Estacionamiento;

import java.util.List;
import java.util.Optional;

public class ServiceEstacionamiento implements IServiceEstacionamiento{
    @Override
    public List<Estacionamiento> findAll() {
        return null;
    }

    @Override
    public Optional<Estacionamiento> getById(Long id) {
        return Optional.empty();
    }
}
