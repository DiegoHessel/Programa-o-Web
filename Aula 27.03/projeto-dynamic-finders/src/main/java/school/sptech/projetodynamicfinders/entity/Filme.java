package school.sptech.projetodynamicfinders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Filme {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.AUTO)// auto incremento
    private Integer id;

    @Size(min = 3)
    @NotBlank
    private String nome;

    @Size(min = 3)
    @NotBlank
    private String diretor;

    @NotNull
    @PositiveOrZero
    @DecimalMax("10.0")
    private  Double nota;

    @NotNull
    @PastOrPresent
    private LocalDate dataLoancamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getDataLoancamento() {
        return dataLoancamento;
    }

    public void setDataLoancamento(LocalDate dataLoancamento) {
        this.dataLoancamento = dataLoancamento;
    }
}
