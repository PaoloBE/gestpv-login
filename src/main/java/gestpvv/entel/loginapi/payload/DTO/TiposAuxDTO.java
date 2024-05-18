package gestpvv.entel.loginapi.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiposAuxDTO {
    private List<TipoDTO> tiposDoc;
    private List<TipoDTO> tiposUsr;
}
