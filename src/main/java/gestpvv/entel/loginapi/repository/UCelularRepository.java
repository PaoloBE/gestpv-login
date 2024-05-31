package gestpvv.entel.loginapi.repository;
import gestpvv.entel.loginapi.entity.UsuarioCelular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UCelularRepository extends JpaRepository<UsuarioCelular, Long>{

    @Query("SELECT uc FROM UsuarioCelular uc WHERE uc.usuario.idUsuario = :id AND celularEstado = 1")
    UsuarioCelular findCelAct(@Param("id") Integer id);

}
