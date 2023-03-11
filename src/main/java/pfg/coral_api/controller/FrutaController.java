package pfg.coral_api.controller;

import lombok.RequiredArgsConstructor;
import pfg.coral_api.errors.FrutaNotFoundException;
import pfg.coral_api.model.Fruta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfg.coral_api.repos.FrutaRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FrutaController {

    private final FrutaRepository repositorioFrutas;

    /**
     * Obtención de todas las frutas
     *
     * @return Lista de frutas
     */
    @GetMapping("/frutas")
    public ResponseEntity<List<?>> getAllFrutas() {
        List<Fruta> frutas = repositorioFrutas.findAll();
        if (frutas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(frutas);
        }
    }

    /**
     * Obtención de una fruta según su id
     *
     * @param id Identificador de la fruta
     * @return Fruta | Error 404
     */
    @GetMapping("/fruta/{id}")
    public Fruta getFrutaById(@PathVariable Long id) {
        return repositorioFrutas.findById(id)
                .orElseThrow(() -> new FrutaNotFoundException(id));
    }

}

