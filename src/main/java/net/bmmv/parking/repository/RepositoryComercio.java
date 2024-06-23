package net.bmmv.parking.repository;

import net.bmmv.parking.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryComercio extends JpaRepository<Comercio, Long> {

    public Comercio findByCuit(Long cuit);

}

