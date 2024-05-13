package gestpvv.entel.loginapi.payload;

import gestpvv.entel.loginapi.payload.model.TipoPermiso;
import gestpvv.entel.loginapi.payload.model.PersonaCliente;
import gestpvv.entel.loginapi.payload.model.TipoUsuario;
import lombok.Data;

@Data
public class ReqUsuarioRegister {
    private String usuarioDesc;
    private TipoPermiso tipoPermiso;
    private TipoUsuario tipoUsuario;
    private PersonaCliente personaCliente;
}
