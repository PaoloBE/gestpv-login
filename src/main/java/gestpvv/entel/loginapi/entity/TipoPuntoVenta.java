package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tipo_punto_venta", schema = "Admin")
public class TipoPuntoVenta {
    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_punto_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPuntoVenta;

    @Column(name = "punto_venta_desc")
    private String puntoVentaDesc;

    @Column(name = "punto_venta_estado")
    private Integer puntoVentaEstado;

    @Column(name = "punto_venta_fecha_creacion")
    private OffsetDateTime puntoVentaFechaCreacion;

    @Column(name = "punto_venta_fecha_modifica")
    private OffsetDateTime puntoVentaFechaModifica;

    @OneToMany(mappedBy = "tipoPuntoVenta")
    private Set<PersonaCliente> personaClientes;
}
