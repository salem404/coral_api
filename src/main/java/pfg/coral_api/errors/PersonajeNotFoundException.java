package pfg.coral_api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonajeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43876691117560211L;

    public PersonajeNotFoundException(Long id) {
        super("Personaje inexistente: " + id);
    }
}
