package school.sptech.atividadenota.dto;

public class ProdutoListagemDto {
    private Integer id;
    private String nome;
    private String fabricante;
    private String categoria;
    private Integer quantidadeEstoque;
    private Integer quantidadeVendidos;
    private double precoVenda;
    private double precoCompra;

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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getQuantidadeVendidos() {
        return quantidadeVendidos;
    }

    public void setQuantidadeVendidos(Integer quantidadeVendidos) {
        this.quantidadeVendidos = quantidadeVendidos;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }


    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getLucro() {
        return (precoVenda - precoCompra) * quantidadeVendidos;
    }
}
