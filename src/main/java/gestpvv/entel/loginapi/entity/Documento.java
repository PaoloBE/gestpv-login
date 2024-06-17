package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "documento")
public class  Documento {

    public Documento(String desc, Integer estado, TipoDocumentoIdentidad tipo, PersonaCliente persona){
        this.documentoDesc = desc;
        this.documentoEstado = estado;
        this.documentoFechaCreacion = OffsetDateTime.now();
        this.documentoFechaModifica = OffsetDateTime.now();
        this.documentoTipoDocumento = tipo;
        this.personaCliente = persona;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @Column(length = 45, name = "documento_desc")
    private String documentoDesc;

    @Column(name = "documento_estado")
    private Integer documentoEstado;

    @Column(name = "documento_fecha_creacion")
    private OffsetDateTime documentoFechaCreacion;

    @Column(name = "documento_fecha_modifica")
    private OffsetDateTime documentoFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_cliente_id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_documento_id_tipo_documento", referencedColumnName = "id_tipo_documento", nullable = false)
    private TipoDocumentoIdentidad documentoTipoDocumento;
}
