package net.bmmv.parking.repository;

import io.swagger.v3.oas.annotations.Hidden;
import net.bmmv.parking.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


@Hidden
public interface RepositoryComercio extends JpaRepository<Comercio, Long> {

    public Comercio findByCuit(Long cuit);

    @Query("FROM Comercio c WHERE c.id_comercio = :id_comercio")
    Optional<Comercio> findComercioById(@Param("id_comercio") Long id_comercio);

}

