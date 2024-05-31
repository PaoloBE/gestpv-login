package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
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
    public Direccion(Ubigeo ubi, PersonaCliente pers){
        this.ubigeo = ubi;
        this.direccionEstado = 1;
        this.personaCliente = pers;
        this.direccionFechaModifica = OffsetDateTime.now();
        this.direccionFechaCreacion = OffsetDateTime.now();
    }

    public Direccion(String desc, String ref,
                     String alt, String lat, String lon,
                     String zone, String ciudad, PersonaCliente personaCliente, Ubigeo ubigeo) {
        this.direccionDesc = desc;
        this.direccionEstado = 1;
        this.direccionFechaModifica = OffsetDateTime.now();
        this.direccionFechaCreacion = OffsetDateTime.now();
        this.personaCliente = personaCliente;
        this.direccionRef = ref;
        this.direccionAlt = alt;
        this.direccionLat = lat;
        this.direccionLongitud = lon;
        this.direccionZona = zone;
        this.direccionCiudad = ciudad;
        this.ubigeo = ubigeo;
    }

    @Id
    @Column(nullable = false, updatable = false, name = "id_direccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @Column(name = "direccion_desc")
    private String direccionDesc;

    @Column(name = "direccion_estado")
    private Integer direccionEstado;

    @Column(name = "direccion_fecha_creacion")
    private OffsetDateTime direccionFechaCreacion;

    @Column(name = "direccion_fecha_modifica")
    private OffsetDateTime direccionFechaModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_cliente_id_persona_cliente", referencedColumnName = "id_persona_cliente", nullable = false)
    private PersonaCliente personaCliente;

    @Column(name = "direccion_referencia")
    private String direccionRef;

    @Column(name = "direccion_altitud")
    private String direccionAlt;

    @Column(name = "direccion_latitud")
    private String direccionLat;

    @Column(name = "direccion_longitud")
    private String direccionLongitud;

    @Column(name = "direccion_zona")
    private String direccionZona;

    @Column(name = "direccion_ciudad")
    private String direccionCiudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubigeo_id_ubigeo", referencedColumnName = "id_ubigeo")
    private Ubigeo ubigeo;

}
