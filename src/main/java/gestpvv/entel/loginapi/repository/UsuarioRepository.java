package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.TipoPermiso;
import gestpvv.entel.loginapi.entity.TipoUsuario;
import gestpvv.entel.loginapi.entity.Usuario;
import gestpvv.entel.loginapi.entity.UsuarioContrasena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdpersonaClienteIdPersonaCliente(Integer idPersonaCliente);

    @Query("SELECT c.contrasenaDescEncrypt FROM UsuarioContrasena c where c.usuario.idUsuario = :idUsuario and contrasenaEstado = 1")
    String findPasswordHash(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT tp FROM TipoPermiso tp WHERE idTipoPermiso = :id")
    TipoPermiso findTipPerm(@Param("id") Integer id);

    @Query("SELECT tp FROM TipoUsuario tp WHERE idTipoUsuario = :id")
    TipoUsuario findTipUsuario(@Param("id") Integer id);
}
