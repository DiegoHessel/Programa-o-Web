package school.sptech.atividadenota.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.atividadenota.dto.*;
import school.sptech.atividadenota.entity.Produto;
import school.sptech.atividadenota.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoListagemDto> adicionarProduto(@RequestBody @Valid ProdutoCriacaoDto produto) {
        if (produto == null) {
            return ResponseEntity.status(400).build();
        }
        Produto produtoSalvo = ProdutoMapper.toEntity(produto);
        produtoRepository.save(produtoSalvo);
        ProdutoListagemDto exibirProduto = ProdutoMapper.toDto(produtoSalvo);
        return ResponseEntity.status(201).body(exibirProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoListagemDto>> listarProdutos() {

        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<ProdutoListagemDto> produtosDto = ProdutoMapper.toDto(produtos);
        return ResponseEntity.status(200).body(produtosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> buscarProduto(@PathVariable Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        ProdutoListagemDto produtoDto = ProdutoMapper.toDto(produto.get());
        return ResponseEntity.status(200).body(produtoDto);
    }

    @GetMapping("/{id}/cliente")
    public ResponseEntity<ProdutoClienteListagemDto> buscarProdutoPorClientePorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        ProdutoClienteListagemDto produtoCliente = ProdutoMapper.toClienteDto(produto.get());
        return ResponseEntity.status(200).body(produtoCliente);
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<ProdutoListagemDto> atualizarEstoque(@PathVariable Integer id, @RequestBody ProdutoEstoqueAtualizacaoDto quantidade) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Produto produtoAchado = produto.get();
        produtoAchado.setQuantidadeEstoque(quantidade.getNovaQuantidade());
        produtoRepository.save(produtoAchado);
        ProdutoListagemDto produtoNovo = ProdutoMapper.toDto(produtoAchado);
        return ResponseEntity.status(200).body(produtoNovo);
    }

    @PostMapping("{id}/compra")
    public ResponseEntity<ProdutoClienteListagemDto> finalizarCompra(@PathVariable Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Produto finalizarCompra = produto.get();
        finalizarCompra.setQuantidadeVendidos(finalizarCompra.getQuantidadeVendidos() + 1);
        finalizarCompra.setQuantidadeEstoque(finalizarCompra.getQuantidadeEstoque() - 1);
        produtoRepository.save(finalizarCompra);
        ProdutoClienteListagemDto produtoCliente = ProdutoMapper.toClienteDto(finalizarCompra);
        return ResponseEntity.status(200).body(produtoCliente);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<ProdutoClienteListagemDto>> filtrarProduto(@RequestParam String categoria, @RequestParam Double precoInicial,@RequestParam Double precoFinal) {
    List<Produto>produtos = produtoRepository.findByNomeCategoriaContainsAndPrecoVendaBetween(categoria,precoInicial,precoFinal);
    if (produtos.isEmpty()){
        return ResponseEntity.status(404).build();
    }
    List<ProdutoClienteListagemDto> produtosFiltrados = ProdutoMapper.toClienteDto(produtos);
    return ResponseEntity.status(200).body(produtosFiltrados);
    }
    @GetMapping("/mais-vendidos")
    public ResponseEntity<List<ProdutoListagemDto>> buscarProdutosMaisVendidos(@RequestParam Integer quantidadeVendidos) {
        List<Produto> produtos = produtoRepository.findByQuantidadeVendidosGreaterThanEqual(quantidadeVendidos);
        if (produtos.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        List<ProdutoListagemDto> produtosDto = ProdutoMapper.toDto(produtos);
        return ResponseEntity.status(200).body(produtosDto);
    }

}