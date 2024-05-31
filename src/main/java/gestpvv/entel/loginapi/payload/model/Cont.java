package gestpvv.entel.loginapi.payload.model;

import gestpvv.entel.loginapi.entity.Documento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cont {
    public Cont(Documento doc){
        this.tipo = doc.getDocumentoTipoDocumento().getTipoDocumentoDesc();
        this.desc = doc.getDocumentoDesc();
    }
    private String tipo;
    private String desc;
}
