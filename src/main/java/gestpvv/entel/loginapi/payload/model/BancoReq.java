package gestpvv.entel.loginapi.payload.model;

import gestpvv.entel.loginapi.repository.dtos.BancoJPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BancoReq {
    public BancoReq(BancoJPA jpa) {
        this.nombre = jpa.getDesc();
        this.tipoCu = jpa.getTipo();
        this.cuenta = jpa.getNumero();
        this.cuentaCCI = jpa.getNumeroCCI();
    }

    private String nombre;
    private String tipoCu;
    private String cuenta;
    private String cuentaCCI;
}
