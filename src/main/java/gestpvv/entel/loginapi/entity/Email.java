package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "email", schema = "Admin")
public class Email {

    public Email(String desc, Integer estado) {
        this.emailDesc = desc;
        this.emailEstado = estado;
    }

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idemail;

    @Column(name = "email_estado")
    private Integer emailEstado;

    @Column(name = "email_desc")
    private String emailDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_cliente_idpersona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaClienteIdpersonaCliente;

}
