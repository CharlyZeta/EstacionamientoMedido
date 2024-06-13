package net.bmmv.parking.service;

import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.repository.RepositoryRecarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRecarga implements IServiceRecarga  {

    @Autowired
    private RepositoryRecarga repoRecargas;
    @Override
    public List<Recarga> listarTodas() {
        return repoRecargas.findAll();
    }

    @Override
    public List<Recarga> listarRecargasPorPatente(String patente) {
        return repoRecargas.findRecargasByPatente(patente);
    }

    @Override
    public List<Recarga> listarRecargasPorUsuario(Usuario usuario) {
        return repoRecargas.findRecargasByUsuario(usuario);
    }

    @Override
    public List<Recarga> listarRecargasPorComercio(Comercio comercio) {
        return repoRecargas.findRecargasByComercio(comercio);
    }

    @Override
    public Recarga guardar(Recarga recarga) {
        Recarga r = (Recarga) repoRecargas.save(recarga);
        return r;
    }

    @Override
    public void eliminarRecarga(Recarga Recarga) {

    }
}
