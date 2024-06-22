package net.bmmv.parking.repository;

import net.bmmv.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {

    public Usuario findUsuarioByDni(Long Dni);
}
