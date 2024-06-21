package net.bmmv.parking.service;

import net.bmmv.parking.model.Estacionamiento;
import net.bmmv.parking.repository.RepositoryEstacionamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEstacionamiento implements IServiceEstacionamiento{

    @Autowired
    private RepositoryEstacionamiento repoEstacionamiento;

    @Override
    public List<Estacionamiento> ListarTodos() {
        return repoEstacionamiento.findAll();
    }

//    @Override
//    public Optional<Estacionamiento> BuscarPorPatente(String patente) {
//        return Optional.ofNullable((Estacionamiento) repoEstacionamiento.findAllByPatente_vehiculo(patente));
//    }


    @Override
    public Estacionamiento guardarEstacionamiento(Estacionamiento estacionamiento) {
        return repoEstacionamiento.save(estacionamiento);
    }

}
