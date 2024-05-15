package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "persona_cliente", schema = "Admin")
public class PersonaCliente {

    public PersonaCliente(){

    }

    public PersonaCliente(String nom, String apeP, String apeM, String naci, Integer estado){
        this.personaNombres = nom;
        this.personaPrimerApellido = apeP;
        this.personaSegundoApellido = apeM;
        this.personaNacimiento = naci;
        this.personaFechaCreacion = OffsetDateTime.now();
        this.personaFechaModifica = OffsetDateTime.now();
        this.personaEstado = estado;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_persona_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonaCliente;

    @Column(length = 100, name = "persona_nombres")
    private String personaNombres;

    @Column(length = 45, name = "persona_primer_apellido")
    private String personaPrimerApellido;

    @Column(length = 45, name = "persona_segundo_apellido")
    private String personaSegundoApellido;

    @Column(length = 45, name = "persona_ruta_imagen_documento")
    private String personaRutaImagenDocumento;

    @Column(length = 45, name = "persona_razon_social")
    private String personaRazonSocial;

    @Column(length = 45, name = "persona_tipo_punto_venta")
    private String personaTipoPuntoVenta;

    @Column(name = "persona_estado")
    private Integer personaEstado;

    @Column(name = "persona_fecha_creacion")
    private OffsetDateTime personaFechaCreacion;

    @Column(name = "persona_fecha_modifica")
    private OffsetDateTime personaFechaModifica;

    @Column(name = "persona_nacimiento")
    private String personaNacimiento;

    @OneToMany(mappedBy = "personaCliente")
    private Set<Direccion> personaClienteDireccions;

    @OneToMany(mappedBy = "personaCliente")
    private Set<Documento> personaClienteDocumentoes;

    @OneToMany(mappedBy = "personaClienteIdpersonaCliente")
    private Set<Email> personaClienteIdpersonaClienteEmails;

    @OneToMany(mappedBy = "idpersonaCliente")
    private Set<Telefono> idpersonaClienteTelefonoes;

    @OneToMany(mappedBy = "idpersonaCliente")
    private Set<Usuario> idpersonaClienteUsuarios;

}
