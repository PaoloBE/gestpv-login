package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.UsuarioCorreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UCorreoRepository  extends JpaRepository<UsuarioCorreo, Long> {

    @Query("SELECT uc FROM UsuarioCorreo uc WHERE uc.usuario.idUsuario = :id AND correoEstado = 1")
    UsuarioCorreo findCorreoAct(@Param("id") Integer id);
}
