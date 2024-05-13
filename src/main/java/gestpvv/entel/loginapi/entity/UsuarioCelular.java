package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;


@Data
@Entity
@Table(name = "usuario_celular", schema = "Admin")
public class UsuarioCelular {

    public UsuarioCelular(String celularNumeroDesc, Integer celularEstado, Usuario usuario) {
        this.usuario = usuario;
        this.celularEstado = celularEstado;
        this.celularNumeroDesc = celularNumeroDesc;
        this.celularFechaModifica = OffsetDateTime.now();
        this.celularFechaCreacion = OffsetDateTime.now();
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_usuario_celular")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioCelular;

    @Column(length = 45, name = "celular_numero_desc")
    private String celularNumeroDesc;

    @Column(name = "celular_estado")
    private Integer celularEstado;

    @Column(name = "celular_fecha_creacion")
    private OffsetDateTime celularFechaCreacion;

    @Column(name = "celular_fecha_modifica")
    private OffsetDateTime celularFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    private Usuario usuario;
}
