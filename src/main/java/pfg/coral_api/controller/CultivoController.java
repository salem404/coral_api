package pfg.coral_api.controller;

import lombok.RequiredArgsConstructor;
import pfg.coral_api.errors.CultivoNotFoundException;
import pfg.coral_api.model.Cultivo;
import pfg.coral_api.repos.CultivoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CultivoController {

    private final CultivoRepository repositorioCultivos;

    /**
     * Obtención de todos los cultivos
     *
     * @return Lista de cultivos
     */
    @GetMapping("/cultivos")
    public ResponseEntity<List<?>> getAllCultivos() {
        List<Cultivo> cultivos = repositorioCultivos.findAll();
        if (cultivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cultivos);
        }
    }

    /**
     * Obtención de un cultivo según su id
     *
     * @param id Identificador del cultivo
     * @return Cultivo | Error 404
     */
    @GetMapping("/cultivo/{id}")
    public Cultivo getCultivoById(@PathVariable Long id) {
        return repositorioCultivos.findById(id)
                .orElseThrow(() -> new CultivoNotFoundException(id));
    }

}
