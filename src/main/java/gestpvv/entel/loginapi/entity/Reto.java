package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "reto", schema = "Admin")
public class Reto {

    @Id
    @Column(nullable = false, updatable = false, name = "id_reto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idReto;

    @Column(name = "reto_desc")
    private String retoDesc;

    @Column(name = "reto_canal")
    private String retoCanal;

    @Column(name = "reto_estado")
    private String retoEstado;

    @Column(name = "reto_metrica")
    private String retoMetr;

    @Column(name = "reto_inicio")
    private OffsetDateTime retoInicio;

    @Column(name = "reto_fin")
    private OffsetDateTime retoFin;

    @Column(name = "reto_metrica_total")
    private String retoMTotal;

    @Column(name = "reto_kpi")
    private String retoKpi;

    @Column(name = "reto_fecha_creacion")
    private OffsetDateTime retoFechaCreacion;

    @Column(name = "reto_fecha_modifica")
    private OffsetDateTime retoFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productos_id_producto", referencedColumnName = "id_producto")
    private Producto producto;
}
