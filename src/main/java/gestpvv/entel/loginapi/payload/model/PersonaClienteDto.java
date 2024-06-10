package gestpvv.entel.loginapi.payload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaClienteDto {
    public PersonaClienteDto(String nombres, String priApe, String secApe, String nacimiento) {
        this.nombres = nombres;
        this.priApe = priApe;
        this.secApe = secApe;
        this.nacimiento = nacimiento;
    }

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
}
