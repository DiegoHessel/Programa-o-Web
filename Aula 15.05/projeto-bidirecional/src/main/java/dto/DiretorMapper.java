package dto;

import entity.Diretor;
import entity.Filme;

import java.util.List;

public class DiretorMapper {
    public static DiretorListagemDto toDto(Diretor entity){
        if (entity == null) return null;

     DiretorListagemDto dto = new DiretorListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFilmes(toFilmeDto(entity.getFilme()));
        return dto;
    }
    private static List<DiretorListagemDto.FilmeDto> toFilmeDto(List<Filme> entities) {
        return entities.stream()
                .map(f -> {
                    DiretorListagemDto.FilmeDto dto = new DiretorListagemDto.FilmeDto();
                    dto.setId(f.getId());
                    dto.setTitulo(f.getTitulo());
                    return dto;
                }).toList();
    }
    public static List<DiretorListagemDto> toDto(List<Diretor> entities){
        return entities.stream()
                .map(DiretorMapper::toDto)
                .toList();
    }
}
