package net.bmmv.parking.service;
import jakarta.transaction.Transactional;
import net.bmmv.parking.model.*;

import java.util.List;

public interface IServiceRecarga {

    public List<Recarga> listarTodas();

    public RecargaDTO devuelveRecargaDTO(Recarga recarga) throws Exception;

    public List<Recarga> listarPorPatente(String patente);

    public List<Recarga> listarPorUsuario(Long dniUsuario);

    public List<Recarga> listarPorComercio(Long Comercio);

    /*
    * Toma como parámetro una lista de recargas y devuenve un DTO de recargas
    */
    public List<RecargaDTO> convertirARecargaDTO(List<Recarga> recargas);

    @Transactional
    public Recarga guardar(Recarga recarga);



}
