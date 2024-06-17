package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    public Usuario(String desc, String estado, String padres, PersonaCliente persona, TipoPermiso permiso, TipoUsuario usuario){
        this.usuarioDesc = desc;
        this.usuarioEstado = estado;
        this.idpersonaCliente = persona;
        this.idtipoPermiso = permiso;
        this.idtipoUsuario = usuario;
        this.usuarioFechaCreacion = OffsetDateTime.now();
        this.usuarioFechaModifica = OffsetDateTime.now();
        this.usuarioPadres = padres;
    }

    public Usuario(Integer id, String desc, String estado, PersonaCliente persona, TipoPermiso permiso, TipoUsuario usuario){
        this.idUsuario = id;
        this.usuarioDesc = desc;
        this.usuarioEstado = estado;
        this.idpersonaCliente = persona;
        this.idtipoPermiso = permiso;
        this.idtipoUsuario = usuario;
        this.usuarioFechaCreacion = OffsetDateTime.now();
        this.usuarioFechaModifica = OffsetDateTime.now();
    }

    @Id
    @Column(nullable = false, updatable = false,name = "id_Usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(length = 1000, name = "usuario_desc")
    private String usuarioDesc;

    @Column(name = "usuario_fecha_creacion")
    private OffsetDateTime usuarioFechaCreacion;

    @Column(length = 45, name = "usuario_estado")
    private String usuarioEstado;

    @Column(name = "usuario_fecha_modifica")
    private OffsetDateTime usuarioFechaModifica;

    @Column(name = "usuario_padres")
    private String usuarioPadres;

    @OneToMany(mappedBy = "usuario")
    private Set<Token> usuarioTokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_cliente_id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente idpersonaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_permiso_id_tipo_permiso", referencedColumnName = "id_tipo_permiso", nullable = false)
    private TipoPermiso idtipoPermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id_tipo_usuario", referencedColumnName = "id_tipo_usuario", nullable = false)
    private TipoUsuario idtipoUsuario;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioCelular> usuarioUsuarioCelulars;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioContrasena> usuarioUsuarioContrasenas;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioCorreo> usuarioUsuarioCorreos;

}
