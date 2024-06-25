package net.bmmv.parking.service;

import net.bmmv.parking.model.Comercio;
import net.bmmv.parking.repository.RepositoryComercio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceComercio implements IServiceComercio{

    @Autowired
    private RepositoryComercio repoComercio;

    @Override
    public Comercio guardarComercio(Comercio comercio) {
        comercio.setEstado("Autorizado");
        return repoComercio.save(comercio);
    }

    @Override
    public Comercio actualizarComercioPorCuit(Comercio comercio, Comercio comercioRecibido) {

        comercio.setCuit(comercioRecibido.getCuit());
        comercio.setRazon_social(comercioRecibido.getRazon_social());
        comercio.setDireccion(comercioRecibido.getDireccion());
        comercio.setEstado(comercioRecibido.getEstado());

        return repoComercio.save(comercio);
    }

    @Override
    public Comercio consultarComercio(Long cuit) {

        return repoComercio.findByCuit(cuit);
    }

    @Override
    public List<Comercio> listarComercios() {


        return repoComercio.findAll();    // Ver opcional
    }

    @Override
    public Comercio bajaComercio(Comercio comercio) {   // borrar cuit
        comercio.setEstado("Suspendido");

        return repoComercio.save(comercio);
    }


    @Override
    public Comercio buscarComercioPorCuit(Long cuit) {
        Comercio comercio = repoComercio.findByCuit(cuit);   //Ver implementar Opcional
        return comercio;
    }

    @Override
    public Comercio buscarComercioPorId(Long id) {
        Optional<Comercio> comercioOpt =  repoComercio.findComercioById(id);
        return comercioOpt.orElse(null);
    }

}