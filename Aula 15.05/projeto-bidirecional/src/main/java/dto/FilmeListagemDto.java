package dto;

import lombok.Data;

@Data
public class FilmeListagemDto {

    private Integer id;
    private String titulo;
    private  DiretorDto diretor;
    // nested class = classe statica

    @Data
    public static class DiretorDto{
        private Integer id;
        private String nome;
    }
}
