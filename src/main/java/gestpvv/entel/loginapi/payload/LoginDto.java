package gestpvv.entel.loginapi.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String documentNumberOrEmail;
    private String pass;
}