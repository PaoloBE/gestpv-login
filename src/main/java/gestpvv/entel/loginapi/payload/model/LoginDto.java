package gestpvv.entel.loginapi.payload.model;

import lombok.Data;

@Data
public class LoginDto {
    private Document document;
    private String pass;
}