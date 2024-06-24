package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.AgrupacionTrabajo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgrupacionRepository extends JpaRepository<AgrupacionTrabajo, Long>{

    @Query(value = "select agrupacion_trabajo_usuario_id_padre from agrupacion_trabajo where agrupacion_trabajo_usuario_id_hijo=:id and agrupacion_estado = 1 and tipo_agrupacion_id_tipo_agrupacion=1", nativeQuery = true)
    Integer[] findAgrupacionesByHijos(@Param("id") Integer id);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("update AgrupacionTrabajo a set a.agrupacionEstado = 0 where a.agrupacionHijo=:id")
    void disableAllById(Integer id);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "INSERT INTO `Admin2`.`agrupacion_trabajo`\n" +
            "(agrupacion_estado,agrupacion_fecha_creacion,agrupacion_trabajo_usuario_id_hijo,agrupacion_trabajo_usuario_id_padre,\n" +
            "`tipo_agrupacion_id_tipo_agrupacion`) VALUES(1,NOW(),:hijo,:padre,:tipo)", nativeQuery = true)
    void insertAgrupacion(@Param("hijo") Integer hijo, @Param("padre") Integer padre, @Param("tipo") Integer tipo);

}
