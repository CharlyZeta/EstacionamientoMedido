package net.bmmv.parking.service;

import net.bmmv.parking.model.Comercio;

import java.util.List;

public interface IServiceComercio {

    //Alta de Comercio
    public Comercio guardarComercio(Comercio comercio);

    //Actualizar/Modificar Comercio por CUIT
    public Comercio actualizarComercioPorCuit(Comercio comercio, Comercio comercioRecibido);

    //Consultar un Comercio
    public Comercio consultarComercio (Long cuit);    //DEBERIA DEVOLVER HEATOAS!!!!

    //Listar comercios
    public List<Comercio> listarComercios ();

    //Eliminar un Comercio
    public Comercio bajaComercio(Comercio comercio);

    //Metodos extra
    public Comercio buscarComercioPorCuit(Long cuit);

    public Comercio buscarComercioPorId(Long Id);


}