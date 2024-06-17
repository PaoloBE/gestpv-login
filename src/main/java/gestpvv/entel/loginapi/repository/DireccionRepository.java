package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Direccion;
import gestpvv.entel.loginapi.entity.Ubigeo;
import gestpvv.entel.loginapi.repository.dtos.DireccionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    @Query("SELECT DISTINCT t.ubigeoDep FROM Ubigeo t")
    List<String> findDeps();

    @Query("SELECT DISTINCT t.ubigeoProv FROM Ubigeo t WHERE t.ubigeoDep = :dep")
    List<String> findProvs(@Param("dep") String dep);

    @Query("SELECT DISTINCT t.ubigeoDist FROM Ubigeo t WHERE t.ubigeoProv = :prov")
    List<String> findDists(@Param("prov") String prov);

    @Query("SELECT d.ubigeo.ubigeoDep FROM Direccion d where d.personaCliente.idPersonaCliente = :id")
    String findKAMDep(@Param("id") Integer id);

    @Query("SELECT d.direccionDesc AS desc, d.direccionRef AS ref, d.direccionZona AS zone, " +
            "d.direccionCiudad AS city, d.direccionLat AS lat, d.direccionLongitud AS lon, CONCAT(d.ubigeo.ubigeoDep,'-',d.ubigeo.ubigeoProv,'-',d.ubigeo.ubigeoDist) AS concate  FROM Direccion d where d.personaCliente.idPersonaCliente = :id")
    DireccionJPA findDirPDV(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Admin2.direccion WHERE persona_cliente_id_persona_cliente = :id", nativeQuery = true)
    Direccion findByPersonaId(@Param("id") Integer id);

    @Query(value = "UPDATE Admin2.direccion SET ubigeo_id_ubigeo = :ubigeo WHERE persona_cliente_id_persona_cliente = :id AND direccion_estado = '1'", nativeQuery = true)
    @Modifying
    void updatePersonaDireccionUbigeo(@Param("id") Integer id, @Param("ubigeo") Integer ubigeo);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "INSERT INTO Admin2.direccion\n" +
            "(persona_cliente_id_persona_cliente,direccion_desc,direccion_estado,\n" +
            "direccion_fecha_creacion,ubigeo_id_ubigeo,direccion_referencia,direccion_altitud,\n" +
            "direccion_latitud,direccion_longitud,direccion_zona,direccion_ciudad,direccion_refencia)\n" +
            "VALUES\n" +
            "(:idPer,:desc,1,\n" +
            " NOW(),(SELECT id_ubigeo from Admin2.ubigeo WHERE ubigeo_departamento= :dep AND ubigeo_provincia= :prov AND ubigeo_distrito= :dist),'','',\n" +
            "'','','',:ciudad,'');", nativeQuery = true)
    void insertDireccionBulk(@Param("idPer") Integer idPer, @Param("desc") String desc, @Param("dep") String dep, @Param("prov") String prov, @Param("dist") String dist,@Param("ciudad") String ciudad);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "INSERT INTO Admin2.direccion\n" +
            "(persona_cliente_id_persona_cliente,direccion_desc,direccion_estado,\n" +
            "direccion_fecha_creacion,ubigeo_id_ubigeo,direccion_referencia,direccion_altitud,\n" +
            "direccion_latitud,direccion_longitud,direccion_zona,direccion_ciudad,direccion_refencia)\n" +
            "VALUES\n" +
            "(:idPer,:desc,1,\n" +
            " NOW(),null,'','',\n" +
            "'','','','','');", nativeQuery = true)
    void insertDireccionBulkVend(@Param("idPer") Integer idPer, @Param("desc") String desc);

}
