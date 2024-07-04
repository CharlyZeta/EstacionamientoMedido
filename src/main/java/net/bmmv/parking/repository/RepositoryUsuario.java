package net.bmmv.parking.repository;

import io.swagger.v3.oas.annotations.Hidden;
import net.bmmv.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {

    public Usuario findUsuarioByDni(Long Dni);

    public Usuario findUsuarioByPatenteIgnoreCase(String patente);
}
