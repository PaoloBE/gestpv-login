package gestpvv.entel.loginapi.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    public UsuarioDTO(Integer id, String desc, String estado){
        this.idUsuario = id;
        this.usuarioDesc = desc;
        this.usuarioEstado = estado;
    }

    private Integer idUsuario;
    private String usuarioDesc;
    private String usuarioEstado;
    private PersonaDTO persona;
    private TipoDTO tipoUsuario;
    private TipoDTO celular;
    private TipoDTO correo;
    private TipoDTO permiso;
}
