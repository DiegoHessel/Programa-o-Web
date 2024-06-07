package school.sptech.atividaderelacionamento.dto.tarefa;

import lombok.Data;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoListagemDto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TarefaListagemDto {
    private Integer id;
    private String nome;
    private String descricao;
    private boolean concluida;
    private String prioridade;
    private ProjetoListagemDto projeto;

    public static TarefaListagemDto toDto(Tarefa tarefa) {
        TarefaListagemDto dto = new TarefaListagemDto();
        dto.setId(tarefa.getId());
        dto.setNome(tarefa.getNome());
        dto.setDescricao(tarefa.getDescricao());
        dto.setConcluida(tarefa.isConcluida());
        dto.setPrioridade(tarefa.getPrioridade());
        dto.setProjeto(ProjetoListagemDto.toDto(tarefa.getProjeto()));
        return dto;
    }

    public static List<TarefaListagemDto> toDto(List<Tarefa> tarefas) {
        return tarefas.stream()
                .map(TarefaListagemDto::toDto)
                .collect(Collectors.toList());
    }
}