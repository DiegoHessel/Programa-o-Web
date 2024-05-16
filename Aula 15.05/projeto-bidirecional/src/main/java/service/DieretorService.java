package service;

import entity.Diretor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.DiretorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DieretorService {
    private final DiretorRepository repository;

    public List<Diretor> listar() {
        return repository.findAll();
    }

    public Diretor porId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
}
