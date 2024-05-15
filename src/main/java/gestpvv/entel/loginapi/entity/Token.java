package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "token", schema = "Admin")
public class Token {

    @Id
    @Column(nullable = false, updatable = false, name = "id_token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idToken;

    @Column(length = 45, name = "token_code_desc")
    private String tokenCodeDesc;

    @Column(name = "token_fecha_limite_activacion")
    private OffsetDateTime tokenFechaLimiteActivacion;

    @Column(name = "token_fecha_activacion")
    private OffsetDateTime tokenFechaActivacion;

    @Column(name = "token_estado")
    private Integer tokenEstado;

    @Column(name = "token_fecha_creacion")
    private OffsetDateTime tokenFechaCreacion;

    @Column(name = "token_fecha_modifica")
    private OffsetDateTime tokenFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    private Usuario usuario;

}
