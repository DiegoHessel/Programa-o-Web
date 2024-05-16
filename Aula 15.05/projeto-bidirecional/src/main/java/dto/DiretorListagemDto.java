package dto;

import lombok.Data;

import java.util.List;

@Data
public class DiretorListagemDto {
    private Integer id;
    private String nome;

    private List<FilmeDto> filmes;
    @Data
    public static class FilmeDto{
        private Integer id;
        private String titulo;
    }
}
