package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.TipoGestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GestorRepository extends JpaRepository<TipoGestor, Long> {
    TipoGestor findByIdTipoGestor(Integer idTipo);

}
