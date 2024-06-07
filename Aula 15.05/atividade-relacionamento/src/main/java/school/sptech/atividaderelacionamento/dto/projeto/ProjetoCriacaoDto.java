package school.sptech.atividaderelacionamento.dto.projeto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProjetoCriacaoDto {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}