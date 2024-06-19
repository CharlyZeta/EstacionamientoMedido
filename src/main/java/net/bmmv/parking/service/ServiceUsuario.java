package net.bmmv.parking.service;

import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsuario implements IServiceUsuario{

    @Autowired
    private RepositoryUsuario repoUsuario;
    @Override
    public List<Usuario> ListarUsuarios() {
        return repoUsuario.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorDni(Long Dni) {
        return repoUsuario.findUsuarioByDni(Dni);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repoUsuario.save(usuario);
    }

    @Override
    public Usuario eliminarUsuario(Usuario usuario) {
        repoUsuario.delete(usuario);
        return null;
    }
}
