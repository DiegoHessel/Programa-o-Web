package school.sptech.projeto03statushttp;

public class Produto {
    private String nome;
    private Double preco;
    private Integer qtdEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    // is
   // hes

    // campo virtual== retorna informacoes
    public double getValorTotalEstoque(){
        return preco * qtdEstoque;
    }
}
