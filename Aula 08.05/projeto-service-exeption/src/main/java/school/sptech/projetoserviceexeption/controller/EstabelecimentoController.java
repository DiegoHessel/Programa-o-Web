package school.sptech.projetoserviceexeption.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoserviceexeption.dto.EstabelecimentoCriacaoDto;
import school.sptech.projetoserviceexeption.dto.EstabelecimentoListagemDto;
import school.sptech.projetoserviceexeption.dto.EstabelecimentoMapper;
import school.sptech.projetoserviceexeption.entity.Estabelecimento;
import school.sptech.projetoserviceexeption.service.EstabelecimentoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoService service;
    @GetMapping
    public ResponseEntity<List<EstabelecimentoListagemDto>> lista(){
        List<Estabelecimento> estabelecimentos = service.list();

        if (estabelecimentos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<EstabelecimentoListagemDto> dtos = EstabelecimentoMapper.toDto(estabelecimentos);
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoListagemDto> porId(@PathVariable Integer id){
        Estabelecimento estabelecimento= service.porId(id);
        EstabelecimentoListagemDto dto = EstabelecimentoMapper.toDto(estabelecimento);
        return  ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<EstabelecimentoListagemDto>criar(@RequestBody EstabelecimentoCriacaoDto estabelecimento){

        Estabelecimento estEntity = EstabelecimentoMapper.toEntity(estabelecimento);
        Estabelecimento estSalvo = service.criar(estEntity);
        EstabelecimentoListagemDto dto = EstabelecimentoMapper.toDto(estSalvo);

        URI uri =URI.create("/estabelecimentos/" +estSalvo.getId());

        return ResponseEntity.created(uri).body(dto);
    }
}
