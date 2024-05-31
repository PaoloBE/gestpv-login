package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Reto;
import gestpvv.entel.loginapi.repository.dtos.RetoJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetoRepository extends JpaRepository<Reto, Long> {

    @Query("SELECT r.retoDesc as desc, r.retoMetr as metr, r.retoCanal as canal, r.retoInicio as ini, r.retoFin as fin, r.retoEstado as estado, r.retoMTotal as total, r.retoKpi as kpi, r.producto.productoDesc as prodDesc FROM Reto r")
    List<RetoJPA> findAllRetos();
}
