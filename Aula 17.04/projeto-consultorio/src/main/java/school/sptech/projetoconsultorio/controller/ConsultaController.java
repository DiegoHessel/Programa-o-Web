package school.sptech.projetoconsultorio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoconsultorio.dto.ConsultaCriacaoDto;
import school.sptech.projetoconsultorio.entity.Consulta;
import school.sptech.projetoconsultorio.entity.Medico;
import school.sptech.projetoconsultorio.repository.ConsultaRepository;
import school.sptech.projetoconsultorio.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaRepository repository;
    private final MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> consultas = repository.findAll();
        if (consultas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(consultas);
    }

    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody ConsultaCriacaoDto dto) {
        Optional<Medico> medicoOpt = medicoRepository.findById(dto.getMedicoId());
        if (medicoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Consulta consulta = Consulta.builder()
                .nome(dto.getNome())
                .local(dto.getLocal())
                .dataAgendamento(dto.getDataAgendamento())
                .medico(null)
                .build();

        Consulta consultaSalva = repository.save(consulta);
        return ResponseEntity.created(null).body(consultaSalva);
    }
}
