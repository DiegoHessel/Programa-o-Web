package school.sptech.projetoserviceexeption.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoserviceexeption.entity.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository <Estabelecimento, Integer>{
boolean existsByCnpj(String cnpj);

}

