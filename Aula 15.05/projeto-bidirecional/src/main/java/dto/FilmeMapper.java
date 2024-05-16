package dto;

import entity.Diretor;
import entity.Filme;

import java.util.List;

public class FilmeMapper {
    public static FilmeListagemDto toDto(Filme entity){
        if (entity== null) return null;
        FilmeListagemDto filmeDto =new FilmeListagemDto();
        filmeDto.setId(entity.getId());
        filmeDto.setTitulo(entity.getTitulo());

        filmeDto.setDiretor(toDiretorDto(entity.getDiretor()));
        return filmeDto;
    }
    private static FilmeListagemDto.DiretorDto toDiretorDto(Diretor entity){
        if (entity== null) return null;
        FilmeListagemDto.DiretorDto diretorDto= new FilmeListagemDto.DiretorDto();
        diretorDto.setId(diretorDto.getId());
        diretorDto.setNome(diretorDto.getNome());
        return diretorDto;
    }
    public  static List<FilmeListagemDto> toDto (List<Filme>entities) {
        return entities.stream().map(FilmeMapper::toDto).toList();
    }
    public static Filme toEntity(FilmeCriacaoDto dto){
        if (dto == null) return null;
        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        return filme;
    }

}
