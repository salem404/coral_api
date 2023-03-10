package pfg.coral_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pfg.coral_api.model.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
