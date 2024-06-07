package school.sptech.marketplaceresumido.data;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.marketplaceresumido.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
