package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;


@Data
@Entity
@Table(name = "documento", schema = "Admin")
public class Documento {

    public Documento(String desc, Integer estado, TipoDocumentoIdentidad tipo){
        this.documentoDesc = desc;
        this.documentoEstado = estado;
        this.documentoFechaCreacion = OffsetDateTime.now();
        this.documentoFechaModifica = OffsetDateTime.now();
        this.documentoTipoDocumento = tipo;
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
    @JoinColumn(name = "id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_tipo_documento", referencedColumnName = "id_tipo_documento", nullable = false)
    private TipoDocumentoIdentidad documentoTipoDocumento;
}
