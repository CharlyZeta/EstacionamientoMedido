package net.bmmv.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Hidden
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecargaDTO extends RepresentationModel<RecargaDTO> {

    private Long id_recarga;

    private String patente;

    private float importe;

    private LocalDateTime fecha_hora;

    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JoinColumn(name = "dni") // Clave foránea
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) // Relación uno a muchos
    @JoinColumn(name = "id_Comercio") // Clave foránea
    @JsonIgnore
    private Comercio comercio;

    public RecargaDTO(Recarga recarga) {
    }
}
