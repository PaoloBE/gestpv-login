package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.*;
import gestpvv.entel.loginapi.repository.dtos.UsuariosSuperDTO;
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

    @Query(value = "SELECT usuario_desc FROM Admin.usuario u INNER JOIN Admin.tipo_usuario tu on u.tipo_usuario_id_tipo_usuario = tu.id_tipo_usuario \n" +
            "where tu.tipo_usuario_desc= :desc ORDER BY u.usuario_desc DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastOfTipoUsuario(@Param("desc") String desc);

    @Query(value = "SELECT u.id_usuario as idU, CONCAT(persona_nombres,' ',persona_primer_apellido,' ',persona_segundo_apellido) as nombre \n" +
            ", d.documento_desc as doc, tu.tipo_usuario_desc as tipo FROM Admin.usuario u \n" +
            "inner join Admin.persona_cliente p on u.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "inner join Admin.tipo_usuario tu on u.tipo_usuario_id_tipo_usuario = tu.id_tipo_usuario \n" +
            "inner join Admin.documento d on d.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "where tu.tipo_usuario_desc like :desc ;", nativeQuery = true)
    List<UsuariosSuperDTO> findUsuariosByCriteria(@Param("desc") String desc);

    @Query(value = "SELECT u.id_usuario as idU, CONCAT(persona_nombres,' ',persona_primer_apellido,' ',persona_segundo_apellido) as nombre \n" +
            ", d.documento_desc as doc, tu.tipo_usuario_desc as tipo FROM Admin.usuario u \n" +
            "inner join Admin.persona_cliente p on u.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "inner join Admin.tipo_usuario tu on u.tipo_usuario_id_tipo_usuario = tu.id_tipo_usuario \n" +
            "inner join Admin.documento d on d.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "inner join Admin.tipo_documento_identidad td on d.tipo_documento_id_tipo_documento = td.id_tipo_documento \n" +
            "where  usuario_padres rlike CONCAT('(^|,)',:regex,'($|,)') and td.tipo_documento_desc != 'RUC'" , nativeQuery = true)
    List<UsuariosSuperDTO> findUsuariosByIdPadre(@Param("regex") String regex);

    @Query(value = "SELECT u.id_usuario as idU, CONCAT(persona_nombres,' ',persona_primer_apellido,' ',persona_segundo_apellido) as nombre \n" +
            ", d.documento_desc as doc, tu.tipo_usuario_desc as tipo FROM Admin.usuario u \n" +
            "inner join Admin.persona_cliente p on u.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "inner join Admin.tipo_usuario tu on u.tipo_usuario_id_tipo_usuario = tu.id_tipo_usuario \n" +
            "inner join Admin.documento d on d.persona_cliente_id_persona_cliente = p.id_persona_cliente \n" +
            "where id_usuario in (:list) ", nativeQuery = true)
    List<UsuariosSuperDTO> findUsuariosInList(@Param("list") String[] list);

}
