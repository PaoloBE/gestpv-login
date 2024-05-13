package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "direccion", schema = "Admin")
public class Direccion {

    public Direccion(String desc, Integer estado, PersonaCliente personaCliente) {
        this.direccionDesc = desc;
        this.direccionEstado = estado;
        this.direccionFechaModifica = OffsetDateTime.now();
        this.direccionFechaCreacion = OffsetDateTime.now();
        this.personaCliente = personaCliente;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_direccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @Column(length = 45, name = "direccion_desc")
    private String direccionDesc;

    @Column(name = "direccion_estado")
    private Integer direccionEstado;

    @Column(name = "direccion_fecha_creacion")
    private OffsetDateTime direccionFechaCreacion;

    @Column(name = "direccion_fecha_modifica")
    private OffsetDateTime direccionFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaCliente;

}
