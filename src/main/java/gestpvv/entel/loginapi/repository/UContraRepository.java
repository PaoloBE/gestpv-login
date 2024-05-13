package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.UsuarioContrasena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UContraRepository  extends JpaRepository<UsuarioContrasena, Long> {
}
