package school.sptech.projeto03statushttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/produtos")
public class ProdutoController {
    private List<Produto> produtos = new ArrayList<>();

    // pesquisar sobre o método ResponseEntity
   @PostMapping
   public ResponseEntity<Produto> cadastrar(@RequestBody Produto Novoproduto) {
       produtos.add(Novoproduto);
       return ResponseEntity.status(201).body(Novoproduto);
   }
    @GetMapping
    public ResponseEntity<List<Produto>>listar() {
       if(produtos.isEmpty()){
           return ResponseEntity.status(204).body(produtos);
    }
         return ResponseEntity.status(200).body(produtos);
     }
    @PutMapping("/{indice}")
    public ResponseEntity<Produto> atualizar(@PathVariable int indice, @RequestBody Produto produto) {
        if (indice < produtos.size()) {
            produtos.set(indice, produto);
            return ResponseEntity.status(200).body(produtos.set(indice, produto));
        }
        return ResponseEntity.status(404).build();
   }

    @GetMapping("/estoque/{qtdEstoque}")
    public ResponseEntity <List<Produto>> listarEstoque(@PathVariable int qtdEstoque){
            List<Produto> produtosEstoque = new ArrayList<>();
            for (Produto produto : produtos) {
                if (produto.getQtdEstoque() >= qtdEstoque) {
                    produtosEstoque.add(produto);
                }
            }
            if (produtosEstoque.isEmpty()) {
                return ResponseEntity.status(204).body(produtosEstoque);
            }
            return ResponseEntity.status(200).body(produtosEstoque);
        }
@DeleteMapping("/{indice}")
    public ResponseEntity<Void> excluir(@PathVariable int indice){
        if (indice < produtos.size()) {
            produtos.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/busca-por-nome")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome){
        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().contains(nome)) {
                produtosEncontrados.add(produto);
            }
        }
        if (produtosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtosEncontrados);
    }
}

