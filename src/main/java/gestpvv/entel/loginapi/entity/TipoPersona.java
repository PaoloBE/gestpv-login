package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tipo_persona")
public class TipoPersona {
    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipopersona;

    @Column(name = "tipo_persona_desc")
    private String tipoPersonaDesc;

    @Column(name = "tipo_persona_estado")
    private Integer tipoPersonaEstado;

    @Column(name = "tipo_persona_fecha_creacion")
    private OffsetDateTime tipoPersonaFechaCreacion;

    @Column(name = "tipo_persona_fecha_modifica")
    private OffsetDateTime tipoPersonaFechaModifica;

    @OneToMany(mappedBy = "tipoPersona")
    private Set<PersonaCliente> personaClientes;
}
