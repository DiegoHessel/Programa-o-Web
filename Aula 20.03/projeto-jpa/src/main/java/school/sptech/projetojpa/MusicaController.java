package school.sptech.projetojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    @Autowired
    private MusicaRepository musicaRepository;

    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody Musica novaMusica) {

        Musica musicaSalva = musicaRepository.save(novaMusica);
        return ResponseEntity.status(201).body(musicaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listar() {
        List<Musica> musicas = musicaRepository.findAll();
        if (musicas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(musicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> listarPorId(@PathVariable int id) {
        Optional<Musica> musicaOpt = musicaRepository.findById(id);
        if (musicaOpt.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(musicaOpt.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        if (musicaRepository.existsById(id)){
            musicaRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();

    }
}