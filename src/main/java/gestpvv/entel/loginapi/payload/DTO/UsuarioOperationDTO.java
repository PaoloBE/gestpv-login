package gestpvv.entel.loginapi.payload.DTO;

import gestpvv.entel.loginapi.payload.model.PersonaClienteDto;
import gestpvv.entel.loginapi.payload.model.TipoUsuarioDTO;
import gestpvv.entel.loginapi.repository.dtos.UsuariosSuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOperationDTO {
    public UsuarioOperationDTO(String tipo, String usuarioDesc, TipoUsuarioDTO tipoUsuario) {
        this.tipo = tipo;
        this.usuarioDesc = usuarioDesc;
        this.tipoUsuario = tipoUsuario;
    }

    private String tipo;
    private String usuarioDesc;
    private TipoUsuarioDTO tipoUsuario;
    private PersonaClienteDto personaCliente;
    private String padres;
    private List<UsuariosSuperDTO> padresList;
}
