package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Hidden
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recargas")
public class Recarga extends RepresentationModel<Recarga> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recarga;

    @NotBlank(message = "La patente no puede estar vacía")
    private String patente;

    @NotNull(message = "El monto de la recarga no puede ser nulo o negativo")
    @PositiveOrZero(message = "La recarga no puede ser menor o igual a cero!")
    private Float importe;

    private LocalDateTime fecha_hora;

    @ManyToOne // Relación uno a muchos
    @JoinColumn(name = "dni") // Clave foránea
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne // Relación uno a muchos
    @JoinColumn(name = "id_Comercio") // Clave foránea
    @JsonIgnore
    private Comercio comercio;

}
