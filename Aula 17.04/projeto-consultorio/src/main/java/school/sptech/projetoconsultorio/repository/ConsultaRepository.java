package school.sptech.projetoconsultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.projetoconsultorio.entity.Consulta;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {


    List<Consulta> findByDataAgendamentoBetween(LocalDate datInicio, LocalDate datafim);

    @Query("SELECT c FROM Consulta c WHERE c.dataAgendamento >= ?1 AND c.dataAgendamento <= ?2")
    List<Consulta> buscaEntreDatas(LocalDate datInicio, LocalDate datafim);
}

