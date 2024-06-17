package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonaClienteRepository extends JpaRepository<PersonaCliente, Long> {
    @Query("SELECT d.personaCliente FROM Documento d where d.documentoTipoDocumento.tipoDocumentoDesc = :tipo and d.documentoDesc = :numero")
    Optional<PersonaCliente> findbypersonaClienteDocumentoes(@Param("tipo") String tipo, @Param("numero") String numero);

    @Query("SELECT p FROM PersonaCliente p where idPersonaCliente = :id")
    PersonaCliente findBy(@Param("id") Integer id);

    @Query("SELECT t FROM TipoDocumentoIdentidad t where idTipoDocumento = :id")
    TipoDocumentoIdentidad findTipoDocById(@Param("id") Integer id);

    @Query("SELECT t FROM TipoDocumentoIdentidad t where tipoDocumentoDesc = :desc")
    TipoDocumentoIdentidad findTipoDocByDesc(@Param("desc") String desc);

    @Query("SELECT t FROM TipoTelefono t where tipoTelefonoDesc = :desc")
    TipoTelefono findTipoTelByDesc(@Param("desc") String desc);

    @Query("SELECT t FROM TipoTelefono t where idTipoTelefono = :id")
    TipoTelefono findTipoTelById(@Param("id") Integer id);

    @Query("SELECT t FROM TipoTelefono t where tipoTelefonoEstado = 1")
    List<TipoTelefono> findTipoTelAct();

    @Query("SELECT t FROM Documento t where t.personaCliente.idPersonaCliente = :id AND documentoEstado = 1 AND t.documentoTipoDocumento.tipoDocumentoDesc != 'RUC'")
    Documento findDocByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT t FROM Documento t where t.personaCliente.idPersonaCliente = :id AND documentoEstado = 1 AND t.documentoTipoDocumento.tipoDocumentoDesc = 'RUC'")
    Documento findDocEmpByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT t FROM Telefono t where t.idpersonaCliente.idPersonaCliente = :id AND telefonoEstado = 1")
    Telefono findTelByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT t.telefonoDesc FROM Telefono t where t.idpersonaCliente.idPersonaCliente = :id AND telefonoEstado = 1")
    String findTelDescByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT d FROM Direccion d where d.personaCliente.idPersonaCliente = :id AND direccionEstado = 1")
    Direccion findDireccionByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT d FROM Email d where d.personaClienteIdpersonaCliente.idPersonaCliente = :id AND emailEstado = 1")
    Email findEmailByPersonaIdAct(@Param("id") Integer id);

    @Query("SELECT d.emailDesc FROM Email d where d.personaClienteIdpersonaCliente.idPersonaCliente = :id AND emailEstado = 1")
    String findEmailDescByPersonaIdAct(@Param("id") Integer id);

    @Query(value = "UPDATE Admin2.persona_cliente SET tipo_gestor_id_tipo_gestor = :id", nativeQuery = true)
    @Modifying
    void updateGestorType(@Param("id") Integer id);
}
