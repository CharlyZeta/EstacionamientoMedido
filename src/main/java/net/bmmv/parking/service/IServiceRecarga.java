package net.bmmv.parking.service;
import net.bmmv.parking.model.*;

import java.util.List;

public interface IServiceRecarga {

    public List<Recarga> listarTodas();

    public List<Recarga> listarRecargasPorPatente(String patente);

    public List<Recarga> listarRecargasPorUsuario(Usuario usuario);

    public List<Recarga> listarRecargasPorComercio(Comercio comercio);

    public Recarga guardar(Recarga recarga);

    public void eliminarRecarga(Recarga Recarga);


}
