package school.sptech.projetodynamicfinders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetodynamicfinders.entity.Filme;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository <Filme,Integer>{

    //Dynamic Finder
List<Filme> findByNomeContainsIgnoreCase(String nome);

    List<Filme> findByGreaterThen(Double nota);
    List<Filme> findByGreaterThenEqual(Double nota);
    List<Filme> findByLessThen(Double nota);
    List<Filme> findByLessThenEqual(Double nota);
    Optional<Filme> findFirstByOrderByNotaDesc();
    Optional<Filme> findTop1ByOrderByNotaDesc();
    Optional<Filme> findTopByOrderByNotaDesc();
    List<Filme> findTop3ByOrderByNotaDesc();

    int countByDiretor(String diretor);
    boolean existsByDiretor(String diretor);

}
