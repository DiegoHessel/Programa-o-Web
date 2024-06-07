package school.sptech.marketplaceresumido.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.marketplaceresumido.entity.Produto;
import school.sptech.marketplaceresumido.data.ProdutoRepository;
import school.sptech.marketplaceresumido.exception.EntidadeNaoEncontradaException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscaPorId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Produto")
        );
    }

    public Produto salvar(Produto produtoNovo) {
        return repository.save(produtoNovo);
    }

    public Produto atualizar(int id, Produto produtoAtualizado) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Produto");
        }

        produtoAtualizado.setId(id);

        return repository.save(produtoAtualizado);
    }

}
