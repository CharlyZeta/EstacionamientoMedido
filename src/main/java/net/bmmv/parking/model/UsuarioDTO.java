package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long dni;

    private String nombre;

    private String apellido;

    private String domicilio;

    private String email;

    private String patente;

    private float saldo_cuenta;


    public UsuarioDTO(Usuario usuario) {
    }
}
