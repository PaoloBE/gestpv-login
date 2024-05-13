package gestpvv.entel.loginapi.repository;

import gestpvv.entel.loginapi.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
