package gestpvv.entel.loginapi.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioName {
    Admin("A"),KAM("K"),Socio("S"),Gestor("G"),PDV("P"),PDVX("PX"),Vendedor("V"),Supervisor("SP"),Lider("L"), Itinerante("I");

    private String code;

    TipoUsuarioName(String code) {
        this.code = code;
    }
}