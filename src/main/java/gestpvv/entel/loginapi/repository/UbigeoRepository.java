package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.TipoGestor;
import gestpvv.entel.loginapi.entity.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Long> {
    Ubigeo findByIdUbigeo(Integer idUbigeo);

    @Query("SELECT u FROM Ubigeo u WHERE u.ubigeoDep = :dep AND u.ubigeoInei LIKE '%101'")
    Ubigeo findMainDep(@Param("dep") String dep);

    @Query("SELECT u FROM Ubigeo u WHERE u.ubigeoDep = :dep AND u.ubigeoProv = :pro AND u.ubigeoDist = :dist")
    Ubigeo findByDepProDist(@Param("dep") String dep, @Param("pro") String pro, @Param("dist") String dist);
}
