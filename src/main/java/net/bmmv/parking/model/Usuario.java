package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Table(name="usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "dni" ))
public class Usuario extends RepresentationModel<Usuario> {

    @Id
    @NotNull(message = "El DNI no puede estar vacío")
    @Positive
    //@ValidateDni
    private Long dni;

    @NotNull(message = "El nombre no puede estar vacío") @Size(min=3, max = 255)
    private String nombre;

    @NotNull(message = "El apellido no puede estar vacío") @Size(min= 3, max = 255)
    private String apellido;

    @NotBlank(message = "El domicilio no puede estar vacío") @Size(max = 255)
    private String domicilio;

    @NotBlank(message = "El email no puede estar vacío") @Email @Column(nullable = false)
    private String email;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía") @Past
    private LocalDate fecha_nacimiento;

    @Size(min=7, max = 9)
    @NotBlank(message = "La patente no puede estar vacía")
    private String patente;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min=6, message = "La contraseña debe tener al menos 6 caracteres!")
    private String contrasena;


    private float saldo_cuenta;

    @OneToMany(mappedBy = "id_estacionamiento")
    @JsonIgnore
    private List<Estacionamiento> registro_estacionameinto;

    @OneToMany(mappedBy = "id_recarga")
    @JsonIgnore
    private List<Recarga> registro_recargas;

}
