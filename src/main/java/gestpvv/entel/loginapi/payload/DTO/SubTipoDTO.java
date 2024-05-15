package gestpvv.entel.loginapi.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTipoDTO {
    public Integer idDir;
    public String desc;
    public TipoDTO subtipo;
}
