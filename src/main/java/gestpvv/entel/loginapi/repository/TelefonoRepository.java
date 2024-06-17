package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "INSERT INTO Admin.telefono(persona_cliente_id_persona_cliente,tipo_telefono_id_tipo_telefono,telefono_desc,telefono_estado,telefono_fecha_creacion)\n" +
            "VALUES(:idPer, (SELECT id_tipo_telefono FROM Admin.tipo_telefono WHERE tipo_telefono_desc='CELULAR TRABAJO'), :desc, 1, NOW());", nativeQuery = true)
    void insertDireccionBulk(@Param("idPer") Integer idPer, @Param("desc") String desc);

}
