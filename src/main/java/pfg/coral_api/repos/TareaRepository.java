package pfg.coral_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pfg.coral_api.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea,Long> {
}
