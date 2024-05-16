package service;

import entity.Diretor;
import entity.Filme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.DiretorRepository;
import repository.FilmeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository repository;
    private final DiretorRepository diretorRepository;
    public List<Filme>listar(){
        return repository.findAll();
    }
    public Filme porId(Integer id){
        return  repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
    public Filme criar(Filme novoFilme, Integer diretorId) {
        Diretor diretor = diretorRepository.findById(diretorId)
                .orElseThrow(() -> new RuntimeException("Diretor not found"));
        novoFilme.setDiretor(diretor);
        return repository.save(novoFilme);
    }
}
