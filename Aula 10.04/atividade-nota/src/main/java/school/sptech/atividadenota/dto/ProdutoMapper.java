package school.sptech.atividadenota.dto;

import school.sptech.atividadenota.entity.Produto;

import java.util.List;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoCriacaoDto dto) {
        if (dto == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setFabricante(dto.getFabricante());
        produto.setCategoria(dto.getCategoria());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setQuantidadeVendidos(produto.getQuantidadeVendidos());
        produto.setPrecoVenda(dto.getPrecoVenda());
        produto.setPrecoCompra(dto.getPrecoCompra());
    return produto;
    }
    public static ProdutoListagemDto toDto(Produto produto){
        if (produto == null) {
            return null;
        }
        ProdutoListagemDto dto= new ProdutoListagemDto();
        dto.setNome(produto.getNome());
        dto.setFabricante(produto.getFabricante());
        dto.setCategoria(produto.getCategoria());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setQuantidadeVendidos(produto.getQuantidadeVendidos());
        dto.setPrecoVenda(produto.getPrecoVenda());
        dto.setPrecoCompra(produto.getPrecoCompra());
        return dto;
    }
    public static ProdutoClienteListagemDto toClienteDto(Produto produto){
        if (produto == null) {
            return null;
        }
        ProdutoClienteListagemDto dto = new ProdutoClienteListagemDto();
        dto.setId(dto.getId());
        dto.setNome(produto.getNome());
        dto.setFabricante(produto.getFabricante());
        dto.setCategoria(produto.getCategoria());
        dto.setPreco(dto.getPreco());
        return dto;
    }


    public static List<ProdutoListagemDto> toDto(List<Produto>produtos) {
        return produtos.stream().map(ProdutoMapper::toDto).toList();
}
    public static List<ProdutoClienteListagemDto> toClienteDto(List<Produto>produtos) {
        return produtos.stream().map(ProdutoMapper::toClienteDto).toList();
    }
}
