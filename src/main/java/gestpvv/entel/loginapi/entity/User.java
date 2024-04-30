package gestpvv.entel.loginapi.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users", schema = "Admin", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iduser;
    private String name;
    private String username;
    private String email;
    private String pass;
    private String documentType;
    private String documentNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idrol"))
    private Set<Role> roles;
}