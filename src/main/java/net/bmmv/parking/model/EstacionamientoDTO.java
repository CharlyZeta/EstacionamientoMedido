package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Hidden
public class EstacionamientoDTO extends RepresentationModel<EstacionamientoDTO> {

    @JsonIgnore
    private Long id_estacionamiento;

    private String patente;

    private String estado;

    private String contrasena;

    public EstacionamientoDTO(Long id_estacionamiento, String patente, String estado, String contrasena) {
        this.id_estacionamiento = id_estacionamiento;
        this.patente = patente;
        this.estado = estado;
        this.contrasena = contrasena;
    }

    public EstacionamientoDTO() {
    }

    public void setId_estacionamiento(Long id_estacionamiento) {
        this.id_estacionamiento = id_estacionamiento;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setEstadoOcupado(){
        this.estado= "Ocupado";
    }
    public void setEstadoLibre(){
        this.estado="Libre";
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstacionamientoDTO that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId_estacionamiento(), that.getId_estacionamiento()) && Objects.equals(getPatente(), that.getPatente()) && Objects.equals(getEstado(), that.getEstado()) && Objects.equals(getContrasena(), that.getContrasena());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId_estacionamiento(), getPatente(), getEstado());
    }
}
