package school.sptech.atividaderelacionamento.service.projeto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.repository.projeto.ProjetoRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoRepository projetoRepository;



    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarProjetoPorId(Integer id){
        return projetoRepository.findById(id).orElse(null);
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

}
