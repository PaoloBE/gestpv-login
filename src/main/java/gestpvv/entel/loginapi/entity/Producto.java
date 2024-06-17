package gestpvv.entel.loginapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @Column(nullable = false, updatable = false, name = "id_Producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idProducto;

    @Column(name = "producto_desc")
    private String productoDesc;

    @Column(name = "producto_stock")
    private Integer productoStock;

    @Column(name = "producto_precio")
    private BigDecimal productoPrecio;

    @Column(name = "producto_estado")
    private String productoEstado;

    @Column(name = "producto_fecha_creacion")
    private OffsetDateTime productoFechaCreacion;

    @Column(name = "producto_fecha_modifica")
    private OffsetDateTime productoFechaModifica;
}
