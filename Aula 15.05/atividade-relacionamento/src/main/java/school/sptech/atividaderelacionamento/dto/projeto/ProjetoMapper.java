package school.sptech.atividaderelacionamento.dto.projeto;

import school.sptech.atividaderelacionamento.dto.tarefa.TarefaMapper;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import java.util.ArrayList;
import java.util.List;

public class ProjetoMapper {

    public static ProjetoListagemDto toDto(Projeto entity) {
        if (entity == null) {
            return null;
        }else {
            ProjetoListagemDto dto = new ProjetoListagemDto();
            dto.setId(entity.getId());
            dto.setNome(entity.getNome());
            dto.setDescricao(entity.getDescricao());
            dto.setDataInicio(entity.getDataInicio());
            dto.setDataFim(entity.getDataFim());
            dto.setTarefas(TarefaMapper.toDto(entity.getTarefas()));
            return dto;
        }
    }

    public static List<ProjetoListagemDto> toDto(List<Projeto> entityList) {
        if (entityList == null) {
            return null;
    }else {
        List<ProjetoListagemDto> dtoList = new ArrayList<>();
        for (Projeto entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }
    }

    public static Projeto toEntity(ProjetoCriacaoDto dto) {
        if (dto == null) {
            return null;
        }else {
            Projeto entity = new Projeto();
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setDataInicio(dto.getDataInicio());
            entity.setDataFim(dto.getDataFim());
            return entity;
        }
    }
}