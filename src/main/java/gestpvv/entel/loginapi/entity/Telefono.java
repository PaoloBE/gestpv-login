package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "telefono", schema = "Admin")
public class Telefono {

    public Telefono(String desc, Integer estado, TipoTelefono tipo, PersonaCliente persona) {
        this.telefonoDesc = desc;
        this.telefonoEstado = estado;
        this.telefonoFechaCreacion = OffsetDateTime.now();
        this.telefonoFechaModifica = OffsetDateTime.now();
        this.tipoTelefono = tipo;
        this.idpersonaCliente = persona;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_telefono")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelefono;

    @Column(length = 45, name = "telefono_desc")
    private String telefonoDesc;

    @Column(name = "telefono_estado")
    private Integer telefonoEstado;

    @Column(name = "telefono_fecha_creacion")
    private OffsetDateTime telefonoFechaCreacion;

    @Column(name = "telefono_fecha_modifica")
    private OffsetDateTime telefonoFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_cliente_id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente idpersonaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_telefono_id_tipo_telefono", referencedColumnName = "id_tipo_telefono", nullable = false)
    private TipoTelefono tipoTelefono;

}
