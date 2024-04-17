package school.sptech.atividadenota.dto;

public class ProdutoClienteListagemDto {
    private Integer id;
    private String nome;
    private String fabricante;
    private String categoria;
    private double precoVenda;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return precoVenda;
    }

    public void setPreco(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
