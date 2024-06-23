package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comercios", uniqueConstraints = @UniqueConstraint(columnNames = "cuit" ))
public class Comercio extends RepresentationModel<Comercio> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comercio;

    @NotNull
    @Digits(integer = 11, fraction = 0, message = "El campo CUIT debe tener exactamente 11 dígitos")
    @Positive(message = "El campo CUIT debe ser un número positivo")
    private Long cuit;

    @NotNull
    private String razon_social;

    @NotBlank
    private String direccion;

    private String estado;

    @OneToMany(mappedBy = "id_recarga")
    @JsonIgnore
    private List<Recarga> registro_recargas;
}