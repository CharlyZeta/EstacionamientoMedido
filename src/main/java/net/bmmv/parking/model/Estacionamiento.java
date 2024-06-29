package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="estacionamientos")
@Entity
public class Estacionamiento extends RepresentationModel<Estacionamiento> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estacionamiento;

    //@NotBlank @Size(min=7, max = 9,message = "La patente debe tener entre 7 y 8 caracteres")
    private String patente;

    private String estado;

    @JsonIgnore
    @Transient
    private String contrasena;

    //@NotBlank @Past(message = "La fecha no puede ser posterior a la fecha actual")
    private LocalDateTime fecha_hora_inicio;

    private LocalDateTime fecha_hora_fin;

    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JsonIgnore
    @JoinColumn(name = "dni") // Clave foránea
    private Usuario usuario;

    public void setEstadoOcupado(){
        this.estado= "Ocupado";
    }
    public void setEstadoLibre(){
        this.estado="Libre";
    }

}
