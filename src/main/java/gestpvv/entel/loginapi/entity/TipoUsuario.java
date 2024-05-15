package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tipo_usuario", schema = "Admin")
public class TipoUsuario {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoUsuario;

    @Column(length = 45, name = "tipo_usuario_desc")
    private String tipoUsuarioDesc;

    @Column(name = "tipo_usuario_estado")
    private Integer tipoUsuarioEstado;

    @Column(name = "tipo_usuario_fecha_creacion")
    private OffsetDateTime tipoUsuarioFechaCreacion;

    @Column(name = "tipo_usuario_fecha_modifica")
    private OffsetDateTime tipoUsuarioFechaModifica;

    @Column(name = "tipo_usuario_jerarquia")
    private Integer tipoUsuarioJerarquia;

    @Column(name = "tipo_usuario_minimo_control")
    private Integer tipoUsuarioMinimoControl;

    @Column(name = "tipo_usuario_maximo_control")
    private Integer tipoUsuarioMaximoControl;

    @OneToMany(mappedBy = "idtipoUsuario")
    private Set<Usuario> idtipoUsuarioUsuarios;

}
