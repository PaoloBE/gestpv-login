package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Direccion;
import gestpvv.entel.loginapi.entity.Ubigeo;
import gestpvv.entel.loginapi.repository.dtos.DireccionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

}
