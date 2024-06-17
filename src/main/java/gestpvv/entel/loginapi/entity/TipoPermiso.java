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
@Table(name = "tipo_permiso")
public class TipoPermiso {

    @Id
    @Column(nullable = false, updatable = false, name = "id_tipo_permiso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPermiso;

    @Column(length = 45, name = "tipo_permiso_desc")
    private String tipoPermisoDesc;

    @Column(name = "tipo_permiso_estado")
    private Integer tipoPermisoEstado;

    @Column(name = "tipo_permiso_fecha_creacion")
    private OffsetDateTime tipoPermisoFechaCreacion;

    @Column(name = "tipo_permiso_fecha_modifica")
    private OffsetDateTime tipoPermisoFechaModifica;

    @OneToMany(mappedBy = "idtipoPermiso")
    private Set<Usuario> idtipoPermisoUsuarios;

}
