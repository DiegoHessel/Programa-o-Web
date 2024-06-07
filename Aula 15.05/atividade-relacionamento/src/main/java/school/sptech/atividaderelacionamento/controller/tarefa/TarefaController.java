package school.sptech.atividaderelacionamento.controller.tarefa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaCriacaoDto;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaListagemDto;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.service.projeto.ProjetoService;
import school.sptech.atividaderelacionamento.service.tarefa.TarefaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;
    private final ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<TarefaListagemDto>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        if (tarefas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<TarefaListagemDto> tarefasDto = TarefaListagemDto.toDto(tarefas);
        return ResponseEntity.ok(tarefasDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaListagemDto> buscarTarefa(@PathVariable Integer id) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        if (tarefa == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(TarefaListagemDto.toDto(tarefa));
    }


    @PostMapping
    public ResponseEntity<TarefaListagemDto> criarTarefa(@RequestBody @Valid TarefaCriacaoDto tarefaCriacaoDto) {

        if (tarefaCriacaoDto.getNome() == null || tarefaCriacaoDto.getNome().trim().isEmpty() ||
                tarefaCriacaoDto.getDescricao() == null || tarefaCriacaoDto.getDescricao().trim().isEmpty() ||
                tarefaCriacaoDto.getPrioridade() == null || tarefaCriacaoDto.getPrioridade().trim().isEmpty() ||
                tarefaCriacaoDto.getProjetoId() == null) {
            return ResponseEntity.status(400).build();
        }
        Projeto projeto = projetoService.buscarProjetoPorId(tarefaCriacaoDto.getProjetoId());
        if (projeto == null) {
            return ResponseEntity.status(404).build();
        }

        Tarefa tarefaEntity = TarefaCriacaoDto.toEntity(tarefaCriacaoDto);
        tarefaEntity.setProjeto(projeto);
        Tarefa tarefaCriada = tarefaService.salvarTarefa(tarefaEntity);
        if (tarefaCriada == null || tarefaCriada.getId() == null){
            return ResponseEntity.status(400).build();
        }
        TarefaListagemDto dto = TarefaListagemDto.toDto(tarefaCriada);
        URI uri = URI.create("/tarefas/" + dto.getId());
        return ResponseEntity.created(uri).body(dto);
    }
}