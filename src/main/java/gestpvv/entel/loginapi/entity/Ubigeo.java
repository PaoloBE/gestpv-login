package gestpvv.entel.loginapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ubigeo")
public class Ubigeo {

    @Id
    @Column(nullable = false, updatable = false, name = "id_ubigeo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUbigeo;

    @Column(name = "ubigeo_reniec")
    private String ubigeoReniec;

    @Column(name = "ubigeo_inei")
    private String ubigeoInei;

    @Column(name = "ubigeo_departamento_inei")
    private String ubigeoDepInei;

    @Column(name = "ubigeo_departamento")
    private String ubigeoDep;

    @Column(name = "ubigeo_provincia_inei")
    private String ubigeoProvInei;

    @Column(name = "ubigeo_provincia")
    private String ubigeoProv;

    @Column(name = "ubigeo_distrito")
    private String ubigeoDist;

    @Column(name = "ubigeo_region")
    private String ubigeoReg;

    @Column(name = "ubigeo_macroregion_inei")
    private String ubigeoMacroInei;

    @Column(name = "ubigeo_macroregion_minsa")
    private String ubigeoMacroMinsa;

    @Column(name = "ubigeo_iso_3166")
    private String ubigeoIso;

    @Column(name = "ubigeo_superficie")
    private String ubigeoSuperf;

    @Column(name = "ubigeo_altitud")
    private String ubigeoAlt;

    @Column(name = "ubigeo_latitud")
    private String ubigeoLat;

    @Column(name = "ubigeo_longitud")
    private String ubigeoLong;

    @Column(name = "ubigeo_estado")
    private String ubigeoEstado;

    @Column(name = "ubigeo_fecha_creacion")
    private String ubigeoFechaCrea;

    @Column(name = "ubigeo_fecha_modifica")
    private String ubigeoFechaMod;

    @Column(name = "ubigeo_frontera")
    private String ubigeoFrontera;

    @OneToMany(mappedBy = "ubigeo")
    private Set<Direccion> direccions;

}
