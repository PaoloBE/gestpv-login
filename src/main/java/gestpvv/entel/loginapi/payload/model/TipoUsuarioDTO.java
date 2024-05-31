package gestpvv.entel.loginapi.payload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuarioDTO {
    private Integer id;
    private String desc;
}
