package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "banco", schema = "Admin")
public class Banco {
    @Id
    @Column(nullable = false, updatable = false, name = "id_banco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBanco;

    @Column(name = "banco_estado")
    private Integer bancoEstado;

    @Column(name = "banco_desc")
    private String bancoDesc;

    @Column(name = "banco_fecha_creacion")
    private OffsetDateTime bancoFechaCreacion;

    @Column(name = "banco_fecha_modifica")
    private OffsetDateTime bancoFechaModifica;

    @Column(name = "banco_numero")
    private String bancoNumero;

    @Column(name = "banco_numero_CCI")
    private String bancoNumeroCCI;

    @Column(name = "banco_tipo_cuenta")
    private String bancoTipoCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaCliente;

}
