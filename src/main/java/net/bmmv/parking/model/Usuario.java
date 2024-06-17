package net.bmmv.parking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
@ToString
public class Usuario {

    @Id
    @NotNull(message = "El DNI no puede estar vacío")
    @Positive
    private Long dni;

    @NotNull(message = "El nombre no puede estar vacío") @Size(min=3, max = 255)
    private String nombre;

    @NotNull(message = "El apellido no puede estar vacío") @Size(min= 3, max = 255)
    private String apellido;

    @NotBlank(message = "El domicilio no puede estar vacío") @Size(max = 255)
    private String domicilio;

    @NotBlank(message = "El email no puede estar vacío") @Email @Column(nullable = false)
    private String email;

    @NotNull @Past
    private LocalDate fecha_nacimiento;

    @Size(min=7, max = 9)
    private String patente;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min=6, message = "La contraseña debe tener al menos 6 caracteres!")
    private String contrasena;


    private float saldo_cuenta;

    @OneToMany(mappedBy = "id_estacionamiento")
    private List<Estacionamiento> registro_estacionameinto;

    @OneToMany(mappedBy = "id_recarga")
    private List<Recarga> registro_recargas;
}
