package net.bmmv.parking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estacionamiento {

    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estacionamiento;

    private Long id_patente;

    //@NotBlank @Size(min=7, max = 9,message = "La patente debe tener entre 7 y 8 caracteres")
    private String patente_vehiculo;
    enum estado_estacionamiento {OCUPADO, LIBRE}

    //@NotNull(message = "El estacionamiento debe estar OCUPADO o VACIO")
    private estado_estacionamiento estado;

    //@NotBlank @Past(message = "La fecha no puede ser posterior a la fecha actual")
    private LocalDateTime fecha_hora_inicio;

    private LocalDateTime fecha_hora_fin;


    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JoinColumn(name = "id_usuario") // Clave foránea
    private Usuario usuario;


}
