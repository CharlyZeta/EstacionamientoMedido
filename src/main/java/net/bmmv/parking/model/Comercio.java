package net.bmmv.parking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comercios")
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comercio;

    //@NotNull @Size(min = 11, max = 11)
    private Long cuit;

    //@NotNull
    private String razon_social;

    //@NotBlank
    private String direccion;
    enum estado_comercio {ACTIVO,INACTIVO};
    //@NotNull
    private estado_comercio estado;
}
