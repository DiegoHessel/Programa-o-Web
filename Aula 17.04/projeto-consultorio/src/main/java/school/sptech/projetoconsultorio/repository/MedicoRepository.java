package school.sptech.projetoconsultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoconsultorio.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
