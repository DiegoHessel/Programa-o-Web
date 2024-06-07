package school.sptech.atividaderelacionamento.dto.tarefa;

import lombok.Data;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;

@Data
public class TarefaCriacaoDto {
    private String nome;
    private String descricao;
    private String prioridade;
    private Integer projetoId;

    public static Tarefa toEntity(TarefaCriacaoDto tarefaCriacaoDto) {
        if (tarefaCriacaoDto == null) {
            return null;
        } else {
            Tarefa tarefa = new Tarefa();
            tarefa.setNome(tarefaCriacaoDto.getNome());
            tarefa.setDescricao(tarefaCriacaoDto.getDescricao());
            tarefa.setPrioridade(tarefaCriacaoDto.getPrioridade());
            return tarefa;
        }
    }

    public static TarefaCriacaoDto toDto(Tarefa tarefa) {
        if (tarefa == null) {
            return null;
        } else {
            TarefaCriacaoDto tarefaCriacaoDto = new TarefaCriacaoDto();
            tarefaCriacaoDto.setNome(tarefa.getNome());
            tarefaCriacaoDto.setDescricao(tarefa.getDescricao());
            tarefaCriacaoDto.setPrioridade(tarefa.getPrioridade());
            if (tarefa.getProjeto() != null) {
                tarefaCriacaoDto.setProjetoId(tarefa.getProjeto().getId());
            }
            return tarefaCriacaoDto;
        }
    }
}