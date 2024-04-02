package school.sptech.avaliacaocontinuada1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    //http://localhost:8080/usuarios
    private final List<Usuario> usuarios = new ArrayList<>();
    private int id;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        if (!emailValido(usuario.getEmail())) {
            return ResponseEntity.status(400).build();
        }
        if (emailExiste(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        usuario.setId(++id);
        usuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    private boolean emailValido(String email) {
        if (email.length() < 10 || !email.contains("@")) {
            return false;

        }
        return true;
    }

    private boolean emailExiste(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().contains(email)) {
                return true;
            }
        }
        return false;
    }
//http://localhost:8080/usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }
//http://localhost:8080/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuario);
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
//http://localhost:8080/usuarios/1
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario, @PathVariable int id) {
        if (!emailValido(usuario.getEmail())) {
            return ResponseEntity.status(400).build();
        }
        if (emailExisteEmOutroUsuario(usuario.getEmail(), id)) {
            return ResponseEntity.status(409).build();
        }
        Usuario usuarioAtualizado = buscarUsuarioPorId(id);
        if (usuarioAtualizado == null) {
            return ResponseEntity.status(404).build();
        }
        usuarioAtualizado.setEmail(usuario.getEmail());
        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioAtualizado.setDataNascimento(usuario.getDataNascimento());
        return ResponseEntity.status(200).body(usuarioAtualizado);
    }

    private boolean emailExisteEmOutroUsuario(String email, int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().contains(email) && usuario.getId() != id) {
                return true;
            }
        }
        return false;
    }
    //http://localhost:8080/usuarios/1

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.status(404).build();
        }
        usuarios.remove(usuario);
        return ResponseEntity.status(200).build();
    }
}
