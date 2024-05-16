package controller;

import dto.FilmeCriacaoDto;
import dto.FilmeListagemDto;
import dto.FilmeMapper;
import entity.Filme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FilmeService;

import java.net.URI;
import java.util.List;

@RequestMapping("/filmes")
@RestController
@RequiredArgsConstructor

public class FilmeController {
    private final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<FilmeListagemDto>> listar() {
        List<Filme> filmes = filmeService.listar();
        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<FilmeListagemDto> dtos = FilmeMapper.toDto(filmes);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeListagemDto> porId(@PathVariable Integer id) {
        Filme filme = filmeService.porId(id);
        return ResponseEntity.ok(FilmeMapper.toDto(filme));
    }


    @PostMapping
    public ResponseEntity<FilmeListagemDto> criar(@RequestBody FilmeCriacaoDto filmeDto) {
        Filme filmeEntity = FilmeMapper.toEntity(filmeDto);
        Filme filmeCriado = filmeService.criar(filmeEntity, filmeDto.getDiretorId());
        FilmeListagemDto dto = FilmeMapper.toDto(filmeCriado);
        URI uri = URI.create("/filmes/" + filmeCriado.getId());
        return ResponseEntity.created(uri).body(dto);
    }
}



