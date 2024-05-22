package gestpvv.entel.loginapi.payload;

import gestpvv.entel.loginapi.payload.model.PersonaClienteDto;
import gestpvv.entel.loginapi.payload.model.TipoUsuario;
import lombok.Data;

@Data
public class ReqUsuarioRegister {
    private String tipo;
    private String usuarioDesc;
    private TipoUsuario tipoUsuario;
    private PersonaClienteDto personaCliente;
}
