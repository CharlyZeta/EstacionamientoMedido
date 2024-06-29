package net.bmmv.parking.repository;

import net.bmmv.parking.model.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(path = "estacionamiento")
public interface RepositoryEstacionamiento extends JpaRepository<Estacionamiento, Long> {

    public Optional<List<Estacionamiento>> findAllByPatente(String patente);

    public Optional<Estacionamiento> findEstacionamientoByPatenteAndEstado(String patente, String estado);

}
