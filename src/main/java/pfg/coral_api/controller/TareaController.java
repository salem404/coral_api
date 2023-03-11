package pfg.coral_api.controller;

import lombok.RequiredArgsConstructor;
import pfg.coral_api.errors.TareaNotFoundException;
import pfg.coral_api.model.Tarea;
import pfg.coral_api.repos.TareaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TareaController {
    private final TareaRepository repositorioTareas;
    private final TareaDTOConverter tareaDTOConverter;
    private final TareaRepository repositorioTareas;

    /**
     * Obtención de todos las tareas
     *
     * @return Lista de tareas
     */
    @GetMapping("/tareas")
    public ResponseEntity<List<?>> getAllTareas() {
        List<Tarea> tareas = repositorioTareas.findAll();
        if (tareas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<TareaDTO> dtoList = tareas.stream().map(tareaDTOConverter::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(tareas);
        }
    }

    /**
     * Obtención de un tarea según su id
     *
     * @param id Identificador de la tarea
     * @return Tarea | Error 404
     */
    @GetMapping("/tarea/{id}")
    public Tarea getTareaById(@PathVariable Long id) {
        return repositorioTareas.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
    }

    /**
     * Creación de una tarea nuevo
     *
     * @param nuevo Datos de la nueva tarea
     * @return Tarea nuevo
     */
    @PostMapping("/tareas")
    public ResponseEntity<?> newTarea(@RequestBody CrearTareaDTO nuevo) {
        Tarea tarea = new Tarea();
        Tarea tarea = null;
        tarea.setNombre(nuevo.getNombre());
        tarea.setAvatar(nuevo.getAvatar());
        if (nuevo.getId_tarea() != null) {
            tarea = repositorioTareas.findById(nuevo.getId_tarea()).orElse(null);
        }
        tarea.setTarea(tarea);
        tarea.setPuntos(nuevo.getPuntos());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioTareas.save(tarea));
    }

    /**
     * Actualización de una tarea según su id
     *
     * @param actualizacion Nuevos datos
     * @param id            Identificación correspondiente
     * @return Tarea editada | Error 404
     */
    @PutMapping("/tarea/{id}")
    public Tarea updateTarea(@RequestBody ModificarTareaDTO actualizacion, @PathVariable Long id) {
        Tarea tarea = null;
        if (actualizacion.getId_tarea() != null) {
            tarea = repositorioTareas.findById(actualizacion.getId_tarea()).orElse(null);
        }
        final Tarea tareaFinal = tarea;
        return repositorioTareas.findById(id).map(tarea -> {
            tarea.setNombre(actualizacion.getNombre());
            tarea.setPuntos(actualizacion.getPuntos());
            tarea.setAvatar(actualizacion.getAvatar());
            tarea.setTarea(tareaFinal);
            return repositorioTareas.save(tarea);
        }).orElseThrow(() -> new TareaNotFoundException(id));
    }

    /**
     * Eliminación de un tarea según su id
     *
     * @param id Identificador del tarea
     * @return Código 204
     */
    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Void> deleteTareaById(@PathVariable Long id) {
        Tarea tarea = repositorioTareas.findById(id).orElseThrow(() -> new TareaNotFoundException(id));
        repositorioTareas.delete(tarea);
        return ResponseEntity.noContent().build();
    }
}