package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.TipoGestor;
import gestpvv.entel.loginapi.repository.dtos.GestJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GestorRepository extends JpaRepository<TipoGestor, Long> {
    TipoGestor findByIdTipoGestor(Integer idTipo);

    @Query("SELECT t FROM TipoGestor t where tipoGestorEstado = 1")
    List<TipoGestor> findTipoGestAct();

    @Query("SELECT g.tipoGestorPersona.idTipoGestor AS idTipo, g.tipoGestorPersona.tipoGestorDesc AS desc FROM PersonaCliente g where g.tipoGestorPersona.tipoGestorEstado = 1 AND g.idPersonaCliente = :id")
    GestJPA findGestorbyId(@Param("id") Integer id);

}
