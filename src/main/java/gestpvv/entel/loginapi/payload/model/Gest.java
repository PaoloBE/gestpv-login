package gestpvv.entel.loginapi.payload.model;

import gestpvv.entel.loginapi.repository.dtos.GestJPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gest {
    public Gest(GestJPA jpa){
        this.idTipo = jpa.getIdTipo();
        this.desc = jpa.getDesc();;
    }
    private Integer idTipo;
    private String desc;
}
