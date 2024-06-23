package net.bmmv.parking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recargas")
public class Recarga extends RepresentationModel<Recarga> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recarga;

    private String patente;
    @NotEmpty
    @PositiveOrZero
    @DecimalMin(value = "0.00f", message = "La recarga no puede ser menor o igual a cero!")
    private float importe;

    @NotNull
    private LocalDateTime fecha_hora;

    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JoinColumn(name = "dni") // Clave foránea
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JoinColumn(name = "id_Comercio") // Clave foránea
    private Comercio comercio;

}
