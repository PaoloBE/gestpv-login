package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.UsuarioCorreo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UCorreoRepository  extends JpaRepository<UsuarioCorreo, Long> {
}
