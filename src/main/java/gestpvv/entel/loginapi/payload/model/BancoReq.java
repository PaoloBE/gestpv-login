package gestpvv.entel.loginapi.payload.model;

import lombok.Data;

@Data
public class BancoReq {
    private String nombre;
    private String tipoCu;
    private String cuenta;
    private String cuentaCCI;
}
