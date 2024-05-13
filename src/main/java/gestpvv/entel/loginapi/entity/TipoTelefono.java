package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "tipo_telefono", schema = "Admin")
public class TipoTelefono {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_telefono")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoTelefono;

    @Column(length = 45, name = "tipo_telefono_desc")
    private String tipoTelefonoDesc;

    @Column(name = "tipo_telefono_estado")
    private Integer tipoTelefonoEstado;

    @Column(name = "tipo_telefono_fecha_creacion")
    private OffsetDateTime tipoTelefonoFechaCreacion;

    @Column(name = "tipo_telefono_fecha_modifica")
    private OffsetDateTime tipoTelefonoFechaModifica;

    @OneToMany(mappedBy = "tipoTelefono")
    private Set<Telefono> tipoTelefonoTelefonoes;

}
