package school.sptech.projetoserviceexeption.dto;

import school.sptech.projetoserviceexeption.entity.Estabelecimento;

import java.util.List;

public class EstabelecimentoMapper {
    public static EstabelecimentoListagemDto toDto (Estabelecimento entity){
        if (entity == null) return  null;

        EstabelecimentoListagemDto dto = new EstabelecimentoListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
    return dto;
    }
    public static List<EstabelecimentoListagemDto> toDto(List<Estabelecimento> entities){
        return entities.stream().map(EstabelecimentoMapper::toDto).toList();
    }
    public static Estabelecimento toEntity(EstabelecimentoCriacaoDto dto){
        if (dto == null) return null;

        Estabelecimento entity = new Estabelecimento();
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        return entity;
    }
}
