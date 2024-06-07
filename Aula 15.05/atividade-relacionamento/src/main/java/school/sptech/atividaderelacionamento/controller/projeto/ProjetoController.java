package school.sptech.atividaderelacionamento.controller.projeto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoCriacaoDto;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoListagemDto;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoMapper;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaCriacaoDto;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaListagemDto;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaMapper;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.service.projeto.ProjetoService;
import school.sptech.atividaderelacionamento.service.tarefa.TarefaService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {
    private final ProjetoService projetoService;
    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<ProjetoListagemDto>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarProjetos();
        if (projetos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            projetos.forEach(projeto -> {
                if (projeto.getTarefas() == null) {
                    projeto.setTarefas(new ArrayList<>());
                }
            });
            return ResponseEntity.ok(ProjetoMapper.toDto(projetos));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoListagemDto> buscarProjeto(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(400).build();
        }

        Projeto projeto = projetoService.buscarProjetoPorId(id);
        if (projeto == null) {
            return ResponseEntity.status(404).build();
        }
        if (projeto.getTarefas() == null) {
            projeto.setTarefas(new ArrayList<>());
        }
        return ResponseEntity.status(200).body(ProjetoMapper.toDto(projeto));
    }

    @PostMapping
    public ResponseEntity<ProjetoListagemDto> criarProjeto(@RequestBody @Valid ProjetoCriacaoDto projetoCriacaoDto) {
        Projeto projetoEntity = ProjetoMapper.toEntity(projetoCriacaoDto);
        List <Tarefa> tarefas = projetoEntity.getTarefas();
        projetoEntity.setTarefas(tarefas);
        Projeto projetoCriado = projetoService.salvarProjeto(projetoEntity);
        if (tarefas == null) {
            return ResponseEntity.status(400).build();
        }
        if (projetoCriado == null) {
            return ResponseEntity.status(400).build();
        }
        for (Tarefa tarefa : tarefas) {
            tarefa.setProjeto(projetoCriado);
            TarefaCriacaoDto tarefaCriacaoDto = TarefaCriacaoDto.toDto(tarefa);
            tarefaService.salvarTarefa(TarefaMapper.toEntity(tarefaCriacaoDto));
        }
        ProjetoListagemDto dto = ProjetoListagemDto.toDto(projetoCriado);
        URI uri = URI.create("/projetos/" + dto.getId());
        return ResponseEntity.status(201).body(dto);
    }
}
