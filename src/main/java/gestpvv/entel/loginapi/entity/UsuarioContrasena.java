package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario_contrasena", schema = "Admin")
public class UsuarioContrasena {

    public UsuarioContrasena(String contrasenaDesc, String contrasenaDescEncrypt, Integer contrasenaEstado, Usuario usuario) {
        this.contrasenaDesc = contrasenaDesc;
        this.contrasenaDescEncrypt = contrasenaDescEncrypt;
        this.contrasenaFechaCreacion = OffsetDateTime.now();
        this.contrasenaEstado = contrasenaEstado;
        this.contrasenaFechaModifica = OffsetDateTime.now();
        this.usuario = usuario;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_usuario_contrasena")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioContrasena;

    @Column(length = 150, name = "contrasena_desc")
    private String contrasenaDesc;
    @Column(length = 150, name = "contrasena_desc_encrypt")
    private String contrasenaDescEncrypt;

    @Column(name = "contrasena_fecha_creacion")
    private OffsetDateTime contrasenaFechaCreacion;

    @Column(name = "contrasena_estado")
    private Integer contrasenaEstado;

    @Column(name = "contrasena_fecha_modifica")
    private OffsetDateTime contrasenaFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_Usuario", nullable = false)
    private Usuario usuario;

}
