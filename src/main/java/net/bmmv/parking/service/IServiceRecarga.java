package net.bmmv.parking.service;
import jakarta.transaction.Transactional;
import net.bmmv.parking.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IServiceRecarga {

    public List<Recarga> listarTodas();

    public Recarga buscarRecargaPorId(Long id);

    public RecargaDTO devuelveRecargaDTO(Recarga recarga) throws Exception;

    public List<Recarga> listarPorPatente(String patente);

    public List<Recarga> listarPorUsuario(Long dniUsuario);

    public List<Recarga> listarPorComercio(Long Comercio);

    /*
    * Toma como parámetro una lista de recargas y devuenve un DTO de recargas
    */
    public List<RecargaDTO> convertirARecargaDTO(List<Recarga> recargas);

    @Transactional
    public Recarga guardar(Recarga recarga) throws Exception;

    public void inyectarLinkUsuarioYComercio(Recarga recarga, Usuario usuarioOpt, Long idComercioRecibido);

    public ResponseEntity<?> validation(BindingResult result);

}
