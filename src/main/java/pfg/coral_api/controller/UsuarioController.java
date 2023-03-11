package pfg.coral_api.controller;

import lombok.RequiredArgsConstructor;
import pfg.coral_api.errors.NotFoundException;
import pfg.coral_api.model.Usuario;
import pfg.coral_api.repos.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repositorioUsuarios;

    /**
     * Obtención de todos los usuarios
     *
     * @return Lista de usuarios
     */
    @GetMapping("/usuarios")
    public ResponseEntity<List<?>> getAllUsuarios() {
        List<Usuario> usuarios = repositorioUsuarios.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(usuarios);
        }
    }

    /**
     * Obtención de un usuario según su id
     *
     * @param id Identificador de lausuario
     * @return Usuario | Error 404
     */
    @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return repositorioUsuarios.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    /**
     * Creación de un usuario nuevo
     *
     * @param nuevo Datos de lanuevo usuario
     * @return Usuario nuevo
     */
    @PostMapping("/usuarios")
    public ResponseEntity<?> newUsuario(@RequestBody Usuario nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioUsuarios.save(nuevo));
    }

    /**
     * Actualización de un usuario según su id
     *
     * @param actualizacion Nuevos datos
     * @param id            Identificación correspondiente
     * @return Usuario editado | Error 404
     */
    @PutMapping("/usuario/{id}")
    public Usuario updateUsuario(@RequestBody Usuario actualizacion, @PathVariable Long id) {
        return repositorioUsuarios.findById(id).map(usuario -> {
            usuario.setUsername(actualizacion.getUsername());
            usuario.setEmail(actualizacion.getEmail());
            usuario.setNombre(actualizacion.getNombre());
            usuario.setApellidos(actualizacion.getApellidos());
            return repositorioUsuarios.save(usuario);
        }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    /**
     * Eliminación de un usuario según su id
     *
     * @param id Identificador de lausuario
     * @return Código 204
     */
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id) {
        Usuario usuario = repositorioUsuarios.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        repositorioUsuarios.delete(usuario);
        return ResponseEntity.noContent().build();
    }

}
