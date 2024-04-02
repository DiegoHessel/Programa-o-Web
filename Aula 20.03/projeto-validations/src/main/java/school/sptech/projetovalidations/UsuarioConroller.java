package school.sptech.projetovalidations;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioConroller {


    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario novoUsuario) {
        usuarios.add(novoUsuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

}
