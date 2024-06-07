package school.sptech.atividaderelacionamento.dto.projeto;

import lombok.Data;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjetoListagemDto {
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<TarefaListagemDto> tarefas;

    public static ProjetoListagemDto toDto(Projeto projeto) {
        if (projeto == null) {
            return null;
        } else {
            ProjetoListagemDto dto = new ProjetoListagemDto();
            dto.setId(projeto.getId());
            dto.setNome(projeto.getNome());
            dto.setDescricao(projeto.getDescricao());
            dto.setDataInicio(projeto.getDataInicio());
            dto.setDataFim(projeto.getDataFim());
            return dto;
        }
    }

    @Data
    public static class TarefaListagemDto {
        private Integer id;
        private String nome;
        private String descricao;
        private boolean concluida;
        private String prioridade;
    }
}