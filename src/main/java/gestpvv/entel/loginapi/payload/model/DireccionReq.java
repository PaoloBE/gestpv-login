package gestpvv.entel.loginapi.payload.model;

import gestpvv.entel.loginapi.repository.dtos.DireccionJPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionReq {
    public DireccionReq(String concat){
        this.ubigeo = new UbigeoReq(concat);
    }
    public DireccionReq(DireccionJPA jpa){
        this.desc = jpa.getDesc();
        this.ubigeo = new UbigeoReq(jpa.getConcate());
        this.city = jpa.getCity();
        this.lat = jpa.getLat();
        this.lon = jpa.getLon();
        this.zona = jpa.getZone();
        this.ref = jpa.getRef();
    }

    private String desc;
    private UbigeoReq ubigeo;
    private String city;
    private String lat;
    private String lon;
    private String zona;
    private String ref;
}
