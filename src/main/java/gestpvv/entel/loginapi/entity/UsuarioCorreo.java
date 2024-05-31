package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario_correo", schema = "Admin")
public class UsuarioCorreo {
    public UsuarioCorreo(String correoDesc, Integer correoEstado, Usuario usuario) {
        this.correoDesc = correoDesc;
        this.correoEstado = correoEstado;
        this.correoFechaCreacion = OffsetDateTime.now();
        this.correoFechaModifica = OffsetDateTime.now();
        this.usuario = usuario;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_usuario_correo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioCorreo;

    @Column(length = 45, name = "correo_desc")
    private String correoDesc;

    @Column(name = "correo_estado")
    private Integer correoEstado;

    @Column(name = "correo_fecha_creacion")
    private OffsetDateTime correoFechaCreacion;

    @Column(name = "correo_fecha_modifica")
    private OffsetDateTime correoFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_Usuario", nullable = false)
    private Usuario usuario;

}
