package pfg.coral_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pfg.coral_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
