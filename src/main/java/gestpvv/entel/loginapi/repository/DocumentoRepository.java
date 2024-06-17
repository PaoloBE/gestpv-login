package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Documento;
import gestpvv.entel.loginapi.entity.TipoDocumentoIdentidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("SELECT d FROM Documento d WHERE d.documentoTipoDocumento.tipoDocumentoDesc = 'RUC' AND d.personaCliente.idPersonaCliente = :id")
    Documento findDocumentBussiness(@Param("id") Integer id);
    
    @Query("SELECT t FROM TipoDocumentoIdentidad t WHERE tipoDocumentoEstado = '1'")
    List<TipoDocumentoIdentidad> findTiposDocsAct();

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "INSERT INTO Admin.documento\n" +
            "(persona_cliente_id_persona_cliente,documento_desc,tipo_documento_id_tipo_documento,documento_estado,documento_fecha_creacion)\n" +
            "VALUES\n" +
            "(:idPer, :desc, (SELECT id_tipo_documento FROM Admin.tipo_documento_identidad WHERE tipo_documento_desc=:tipo),1,NOW())", nativeQuery = true)
    void insertDocumentoBulk(@Param("idPer") Integer idPer, @Param("desc") String desc, @Param("tipo") String tipo);
}
