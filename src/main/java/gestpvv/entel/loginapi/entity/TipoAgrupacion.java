package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tipo_agrupacion")
public class TipoAgrupacion {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_agrupacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoAgrupacion;

    @Column(name = "tipo_agrupacion_desc")
    private String tipoAgrupacionDesc;

    @Column(name = "tipo_agrupacion_estado")
    private Integer tipoAgrupacionEstado;

    @Column(name = "tipo_agrupacion_fecha_creacion")
    private OffsetDateTime tipoAgrupacionFechaCreacion;

    @Column(name = "tipo_agrupacion_fecha_modifica")
    private OffsetDateTime tipoAgrupacionFechaModifica;

}
