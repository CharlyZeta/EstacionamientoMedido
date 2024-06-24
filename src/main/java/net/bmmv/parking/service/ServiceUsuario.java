package net.bmmv.parking.service;

import net.bmmv.parking.controller.ControllerComercio;
import net.bmmv.parking.controller.ControllerEstacionamiento;
import net.bmmv.parking.controller.ControllerUsuario;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.RecargaDTO;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.model.UsuarioDTO;
import net.bmmv.parking.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Usuario> buscarUsuarioPorPatente(String patente) {
        return Optional.ofNullable(repoUsuario.findUsuarioByPatenteIgnoreCase(patente));
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

    public UsuarioDTO devuelveUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO(usuario);
        dto.setDni(usuario.getDni());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDomicilio(usuario.getDomicilio());
        dto.setEmail(usuario.getEmail());
        dto.setPatente(usuario.getPatente());
        dto.setSaldo_cuenta(usuario.getSaldo_cuenta());

        // AGREGAR LINK AL SERVICIO 02 ESTACIONAMIENTO PARA VER EL ESTADO

        return dto;
    }

    public List<UsuarioDTO> convertirAUsuarioDTO(List<Usuario> usuarios){
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        for(Usuario usuario : usuarios){
            usuariosDTO.add(devuelveUsuarioDTO(usuario));
        }
        return usuariosDTO;
    }
}
