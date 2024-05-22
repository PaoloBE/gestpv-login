package gestpvv.entel.loginapi.payload.model;

import lombok.Data;

@Data
public class PersonaClienteDto {
    private String nombres;
    private String priApe;
    private String secApe;
    private String nacimiento;
    private Cont doc;
    private Cont docE;
    private String tel;
    private Gest gest;
    private String razSoc;
    private DireccionReq direccion;
    private String correo;
    private String contraseña;
    private BancoReq banco;
}
