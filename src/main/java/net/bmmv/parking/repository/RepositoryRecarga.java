package net.bmmv.parking.repository;

import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRecarga extends JpaRepository<Recarga, Long> {

    List<Recarga> findRecargasByPatente(String patente);
    List<Recarga> findRecargasByUsuario(Usuario usuario);
    List<Recarga> findRecargasByComercio(Comercio comercio);


}
