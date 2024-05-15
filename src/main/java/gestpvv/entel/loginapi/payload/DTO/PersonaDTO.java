package gestpvv.entel.loginapi.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    public PersonaDTO(Integer idPersona, String nombres, String primerA, String segundoA, String nacimiento) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.primerA = primerA;
        this.segundoA = segundoA;
        this.nacimiento = nacimiento;
    }

    public Integer idPersona;
    public String nombres;
    public String primerA;
    public String segundoA;
    public String nacimiento;
    public TipoDTO email;
    public TipoDTO direccion;
    public SubTipoDTO telefono;
    public SubTipoDTO documento;
}
