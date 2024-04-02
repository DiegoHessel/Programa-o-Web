package school.sptech.projetodynamicfinders.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetodynamicfinders.entity.Filme;
import school.sptech.projetodynamicfinders.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired// cria uma instancia (injeção de dependência)
    private FilmeRepository filmeRepository;

    @PostMapping
    public ResponseEntity<Filme> criar(@RequestBody @Valid Filme novoFilme) {
        novoFilme.setId(null);
        Filme filmeSalvo = filmeRepository.save(novoFilme);
        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable int id) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);
        if (filmeOpt.isPresent()) {
            return ResponseEntity.status(200).body(filmeOpt.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(
            @PathVariable int id, @RequestBody @Valid Filme filmeAtualizado) {
        if (!filmeRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        filmeAtualizado.setId(id);
        Filme filmeSalvo = filmeRepository.save(filmeAtualizado);
        return ResponseEntity.status(200).body(filmeSalvo);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Filme>> buscaPorNome(@RequestParam String nome) {
        List<Filme> filmes = filmeRepository.findByNomeContainsIgnoreCase(nome);
        if (filmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }
@GetMapping("nota")
public ResponseEntity<List<Filme>> buscaPorNota(@RequestParam Double nota){
    List<Filme> filmes = filmeRepository.findByGreaterThen(nota);
    if (filmes.isEmpty()) {
        return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(200).body(filmes);
}

}
