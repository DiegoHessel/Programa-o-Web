package school.sptech.projetoserviceexeption.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.projetoserviceexeption.entity.Estabelecimento;
import school.sptech.projetoserviceexeption.exception.ConflitoException;
import school.sptech.projetoserviceexeption.exception.NaoEncontradoException;
import school.sptech.projetoserviceexeption.repository.EstabelecimentoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    private final EstabelecimentoRepository repository;

    public List<Estabelecimento> list() {
        return repository.findAll();
    }

    public Estabelecimento porId(Integer id){
//        Optional<Estabelecimento> estOpt = repository.findById(id);
//        if (estOpt.isEmpty()){
//            throw new NaoEncontradoException("Estabelecimento");
//        }
//        return estOpt.get();
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Estabelecimento"));
    }
    public Estabelecimento criar(Estabelecimento estabelecimento){

        if (repository.existsByCnpj(estabelecimento.getCnpj())){
            throw new ConflitoException("Estabelecimento CNPJ");
        }
        return repository.save(estabelecimento);
    }

}
