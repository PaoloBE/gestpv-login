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
@Table(name = "tipo_gestor")
public class TipoGestor {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_gestor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoGestor;

    @Column(length = 45, name = "tipo_gestor_desc")
    private String tipoGestorDesc;

    @Column(name = "tipo_gestor_estado")
    private Integer tipoGestorEstado;

    @Column(name = "tipo_gestor_fecha_creacion")
    private OffsetDateTime tipoGestorFechaCreacion;

    @Column(name = "tipo_gestor_fecha_modifica")
    private OffsetDateTime tipoGestorFechaModifica;

    @OneToMany(mappedBy = "tipoGestorPersona")
    private Set<PersonaCliente> tipoGestorPersona;

}
