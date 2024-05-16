package controller;

import dto.DiretorListagemDto;
import dto.DiretorMapper;
import entity.Diretor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DieretorService;
import java.util.List;

@RestController
@RequestMapping("/diretores")
@RequiredArgsConstructor
public class DiretorController {

private final DieretorService service;
    public ResponseEntity <List<DiretorListagemDto>> listar() {
        List<Diretor> diretores = service.listar();
        if (diretores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(DiretorMapper.toDto(diretores));
    }
}
