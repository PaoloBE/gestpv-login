package gestpvv.entel.loginapi.payload.model;

import lombok.Data;

@Data
public class DireccionReq {
    private String desc;
    private UbigeoReq ubigeo;
    private String city;
    private String lat;
    private String lon;
    private String zona;
    private String ref;
}
