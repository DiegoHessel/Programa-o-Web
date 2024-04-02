package school.sptech.projeto02verboshttp;

import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    private List<Musica> musicas = new ArrayList<>();

    // http://localhost:8080/musicas
    @GetMapping
    public List<Musica> listar() {
        return musicas;
    }

    // http:localhost:8080/musicas/0
    @GetMapping("/{indice}")
    public Musica buscarPorIndice(@PathVariable int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            return musicas.get(indice);
        }
        return null;
    }

    // http:localhost:8080/musicas/cadastrar/nao_precisa_manter_distancia/raul_seixas

    @PostMapping
    public Musica cadastrar (@RequestBody Musica novaMusica) {

        musicas.add(novaMusica);
        return novaMusica;

    }
// http:localhost:8080/musicas/atualizar/0/nao_precisa_manter_distancia/raul_seixas
    @PutMapping("/{indice}")
    @RequestBody Musica musicaAtualizada{
    public Musica atualizar (int indice{
        if (indice >= 0 && indice < musicas.size()) {
            Musica musica = musicas.get(indice);
            return musica;
        }
        return null;
    }

// http:localhost:8080/musicas/remover/0
    @DeleteMapping("/{indice}")
    public String remover(@PathVariable int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            musicas.remove(indice);
            return "Musica removida com sucesso";
        }
        return "Musica não encontrada";
    }
}