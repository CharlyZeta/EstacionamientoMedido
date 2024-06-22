package net.bmmv.parking.controller;

import net.bmmv.parking.model.Recarga;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.repository.RepositoryRecarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recargas")
public class ControllerRecarga {

    @Autowired
    private RepositoryRecarga repositoryRecarga;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Recarga> getAllRecargas() {
        return repositoryRecarga.findAll();
    }





}
