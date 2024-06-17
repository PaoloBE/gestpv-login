package gestpvv.entel.loginapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqListUsuario {
    private Integer min;
    private Integer max;
}
