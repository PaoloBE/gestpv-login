package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tipo_documento_identidad")
public class TipoDocumentoIdentidad {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDocumento;

    @Column(length = 45, name = "tipo_documento_desc")
    private String tipoDocumentoDesc;

    @Column(length = 45, name = "tipo_documento_estado")
    private Integer tipoDocumentoEstado;

    @Column(name = "tipo_documento_fecha_creacion")
    private OffsetDateTime tipoDocumentoFechaCreacion;

    @Column(length = 45, name = "tipo_documento_fecha_modifica")
    private OffsetDateTime tipoDocumentoFechaModifica;

    @OneToMany(mappedBy = "documentoTipoDocumento")
    private Set<Documento> documentoTipoDocumentoDocumentoes;

}
