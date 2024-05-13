package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.PersonaCliente;
import gestpvv.entel.loginapi.entity.TipoDocumentoIdentidad;
import gestpvv.entel.loginapi.entity.TipoTelefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonaClienteRepository extends JpaRepository<PersonaCliente, Long> {
    @Query("SELECT d.personaCliente FROM Documento d where d.documentoTipoDocumento.tipoDocumentoDesc = :tipo and d.documentoDesc = :numero")
    Optional<PersonaCliente> findbypersonaClienteDocumentoes(@Param("tipo") String tipo, @Param("numero") String numero);

    @Query("SELECT p FROM PersonaCliente p where idPersonaCliente = :id")
    PersonaCliente findBy(@Param("id") Integer id);

    @Query("SELECT t FROM TipoDocumentoIdentidad t where idTipoDocumento = :id")
    TipoDocumentoIdentidad findTipoDocById(@Param("id") Integer id);

    @Query("SELECT t FROM TipoTelefono t where idTipoTelefono = :id")
    TipoTelefono findTipoTelById(@Param("id") Integer id);
}