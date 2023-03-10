package pfg.coral_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pfg.coral_api.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
