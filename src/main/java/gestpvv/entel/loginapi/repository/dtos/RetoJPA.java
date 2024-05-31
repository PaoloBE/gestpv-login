package gestpvv.entel.loginapi.repository.dtos;

import java.time.OffsetDateTime;

public interface RetoJPA {
    String getDesc();
    String getMetr();
    String getCanal();
    OffsetDateTime getIni();
    OffsetDateTime getFin();
    Character getEstado();
    String getTotal();
    String getKpi();
    String getProdDesc();
}
