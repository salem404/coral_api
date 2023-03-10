package pfg.coral_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pfg.coral_api.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
}
