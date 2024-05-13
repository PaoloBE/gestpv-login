package gestpvv.entel.loginapi.payload.model;

import lombok.Data;

@Data
public class PersonaCliente {
    private String nombres;
    private String priApe;
    private String secApe;
    private String nacimiento;
    private Cont doc;
    private Cont tel;
    private String direccion;
    private String correo;
    private String contraseña;
}
