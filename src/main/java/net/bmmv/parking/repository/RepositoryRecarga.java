package net.bmmv.parking.repository;

import io.swagger.v3.oas.annotations.Hidden;
import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Hidden
@Repository
public interface RepositoryRecarga extends JpaRepository<Recarga, Long> {

    List<Recarga> findAllByPatente(String patente);

    List<Recarga> findAllByUsuarioDni(Long dniUsuario);

    List<Recarga> findAllByComercio(Comercio Comercio);

}
