package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdpersonaClienteIdPersonaCliente(Integer idPersonaCliente);

    @Query("SELECT c.contrasenaDescEncrypt FROM UsuarioContrasena c where c.usuario.idUsuario = :idUsuario and contrasenaEstado = 1")
    String findPasswordHash(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT tp FROM TipoPermiso tp WHERE idTipoPermiso = :id")
    TipoPermiso findTipPerm(@Param("id") Integer id);

    @Query("SELECT tp FROM TipoPermiso tp WHERE tipoPermisoDesc = :desc")
    TipoPermiso findTipPermDesc(@Param("desc") String desc);

    @Query("SELECT tp FROM TipoUsuario tp WHERE idTipoUsuario = :id")
    TipoUsuario findTipUsuario(@Param("id") Integer id);

    @Query("SELECT uc FROM UsuarioCelular uc WHERE uc.usuario.idUsuario = :id AND celularEstado = 1")
    UsuarioCelular findCelAct(@Param("id") Integer id);

    @Query("SELECT uc FROM UsuarioCorreo uc WHERE uc.usuario.idUsuario = :id AND correoEstado = 1")
    UsuarioCorreo findCorreoAct(@Param("id") Integer id);

    @Query("SELECT u FROM Usuario u WHERE idUsuario = :id")
    Optional<Usuario> findById(@Param("id") Integer id);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("update Usuario u set u.usuarioEstado = :estado where u.idUsuario= :id")
    void updateUserState(Integer id, String estado);

    @Query("SELECT t FROM TipoDocumentoIdentidad t WHERE tipoDocumentoEstado = '1'")
    List<TipoDocumentoIdentidad> findTiposDocsAct();

    @Query("SELECT t FROM TipoUsuario t WHERE tipoUsuarioEstado = 1")
    List<TipoUsuario> findTiposUsuarioAct();

    @Query("SELECT tp FROM TipoPermiso tp WHERE tipoPermisoEstado = 1")
    List<TipoPermiso> findTipoPermAct();

    @Query("SELECT u.usuarioDesc FROM Usuario u WHERE u.idtipoUsuario.tipoUsuarioDesc = :desc")
    Optional<String> findLastOfTipoUsuario(@Param("desc") String desc);
}
