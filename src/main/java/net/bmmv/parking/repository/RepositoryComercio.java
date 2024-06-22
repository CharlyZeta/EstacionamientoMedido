package net.bmmv.parking.repository;

import net.bmmv.parking.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryComercio extends JpaRepository<Comercio, Long> {

    public Comercio findByCuit(Long cuit);

}

