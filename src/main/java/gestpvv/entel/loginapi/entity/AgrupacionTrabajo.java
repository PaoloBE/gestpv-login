package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "agrupacion_trabajo")
public class AgrupacionTrabajo {
    public AgrupacionTrabajo(Integer agrupacionHijo, Integer agrupacionPadre, TipoAgrupacion tipoAgrupacion) {
        this.agrupacionEstado = 1;
        this.agrupacionFechaCreacion = OffsetDateTime.now();
        this.agrupacionHijo = agrupacionHijo;
        this.agrupacionPadre = agrupacionPadre;
        this.tipoAgrupacion = tipoAgrupacion;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_agrupacion_trabajo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgrupacion;

    @Column(name = "agrupacion_estado")
    private Integer agrupacionEstado;

    @Column(name = "agrupacion_fecha_creacion")
    private OffsetDateTime agrupacionFechaCreacion;

    @Column(name = "agrupacion_fecha_modifica")
    private OffsetDateTime agrupacionFechaModifica;

    @Column(name = "agrupacion_trabajo_usuario_id_hijo")
    private Integer agrupacionHijo;

    @Column(name = "agrupacion_trabajo_usuario_id_padre")
    private Integer agrupacionPadre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_agrupacion_id_tipo_agrupacion", referencedColumnName = "id_tipo_agrupacion", nullable = false)
    private TipoAgrupacion tipoAgrupacion;

}
