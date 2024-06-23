package net.bmmv.parking.controller;

import net.bmmv.parking.excepcion.ErrorInternoDelServidorExcepcion;
import net.bmmv.parking.excepcion.RecursoNoEncontradoExcepcion;
import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.RecargaDTO;
import net.bmmv.parking.service.IServiceComercio;
import net.bmmv.parking.service.IServiceRecarga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recargas")
@CrossOrigin(value = "http://localhost:3000")   //para frontend en React
public class ControllerRecarga {
    private static final Logger logger = LoggerFactory.getLogger(ControllerRecarga.class);

    @Autowired
    private IServiceRecarga serviceRecarga;
    @Autowired
    private IServiceComercio serviceComercio;

    @GetMapping("/")
    public ResponseEntity<List<RecargaDTO>> obtenerRecargas() throws Exception {
        var recargas = serviceRecarga.listarTodas();
        var recargasDTO = new ArrayList<RecargaDTO>();
        //recargas.forEach(e -> logger.info(e.toString()));
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorPatente(@PathVariable String patente) throws Exception {
        var recargas = serviceRecarga.listarPorPatente(patente);
        var recargasDTO = new ArrayList<RecargaDTO>();
        //recargas.forEach(e -> logger.info(e.toString()));
//        if(patente == null || patente.trim().isEmpty()){
//            logger.error("No se encuentran recargas con la patente: " + patente);
//            throw new RecursoNoEncontradoExcepcion("Debe especificar una patente vehicular");
//        }
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con la patente: " + patente);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con la patente: " + patente);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorDniUsuario(@PathVariable Long dni) throws Exception {
        var recargas = serviceRecarga.listarPorUsuario(dni);
        var recargasDTO = new ArrayList<RecargaDTO>();
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con el DNI: " + dni);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el DNI: " + dni);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

    @GetMapping("/comercio/{cuit}")
    public ResponseEntity<List<RecargaDTO>> obtenerRecargasPorComercio(@PathVariable Long cuit) throws Exception {
        logger.info(cuit.toString());
        Comercio comercio = serviceComercio.buscarComercioPorCuit(cuit);
        if(comercio == null){ throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el CUIT: " + cuit);}
        logger.info(comercio.getId_comercio().toString());
        var recargas = serviceRecarga.listarPorComercio(comercio.getId_comercio());

        var recargasDTO = new ArrayList<RecargaDTO>();
        if(recargas.isEmpty()){
            logger.error("No se encuentran recargas con el CUIT: " + cuit);
            throw new RecursoNoEncontradoExcepcion("No se encuentran recargas con el CUIT: " + cuit);
        }
        for(Recarga recarga : recargas){
            recargasDTO.add(serviceRecarga.devuelveRecargaDTO(recarga));
        }
        return new ResponseEntity<>(recargasDTO, HttpStatus.OK);
    }

}
