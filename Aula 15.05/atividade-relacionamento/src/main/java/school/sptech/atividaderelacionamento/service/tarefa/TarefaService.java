package school.sptech.atividaderelacionamento.service.tarefa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.repository.tarefa.TarefaRepository;

import java.util.List;
@Service
@RequiredArgsConstructor

public class TarefaService {
    private final TarefaRepository tarefaRepository;
    public List<Tarefa> listarTarefas() {

        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Integer id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
}
