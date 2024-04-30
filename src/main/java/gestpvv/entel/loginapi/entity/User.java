package gestpvv.entel.loginapi.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user", schema = "Admin", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"documentNumber"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iduser;
    private String name;
    private String username;
    private String email;
    private String pass;
    @Column(name="documentType")
    private String documentType;
    @Column(name="documentNumber")
    private String documentNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idrole"))
    private Set<Role> roles;
}