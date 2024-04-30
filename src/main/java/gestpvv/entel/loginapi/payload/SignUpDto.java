package gestpvv.entel.loginapi.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String pass;
    private String documentType;
    private String documentNumber;
}