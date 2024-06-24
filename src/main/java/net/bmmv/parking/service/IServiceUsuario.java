package net.bmmv.parking.service;

import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.model.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceUsuario {

    public List<Usuario> ListarUsuarios();

    public Usuario buscarUsuarioPorDni(Long Dni);

    public Optional<Usuario> buscarUsuarioPorPatente(String patente);

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario eliminarUsuario(Usuario usuario);

    public UsuarioDTO devuelveUsuarioDTO(Usuario usuario);

    public List<UsuarioDTO> convertirAUsuarioDTO(List<Usuario> usuarios);

}
