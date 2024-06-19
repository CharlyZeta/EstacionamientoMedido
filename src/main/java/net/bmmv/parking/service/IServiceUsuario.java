package net.bmmv.parking.service;

import net.bmmv.parking.model.Usuario;

import java.util.List;

public interface IServiceUsuario {

    public List<Usuario> ListarUsuarios();

    public Usuario buscarUsuarioPorDni(Long Dni);

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario eliminarUsuario(Usuario usuario);


}
