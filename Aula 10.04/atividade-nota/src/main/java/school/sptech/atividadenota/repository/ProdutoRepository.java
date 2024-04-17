package school.sptech.atividadenota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.atividadenota.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    List<Produto> findByNomeCategoriaContainsAndPrecoVendaBetween(String nomeCategoria, Double precoInicial, Double precoFinal);
    List<Produto> findByQuantidadeVendidosGreaterThanEqual(Integer quantidadeVendidos);
}

