package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.DespesasDto;
import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    private DespesasService despesasService;

    @GetMapping
    public List<DespesasDto> getAllDespesas() {
        return despesasService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesasDto> getDespesaById(@PathVariable Long id) {
        return despesasService.findById(id)
                .map(despesas -> ResponseEntity.ok(toDTO(despesas)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DespesasDto createDespesa(@RequestBody DespesasDto dto) {
        Despesas despesas = fromDTO(dto);
        return toDTO(despesasService.save(despesas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesasDto> updateDespesa(@PathVariable Long id, @RequestBody DespesasDto dto) {
        Despesas despesas = fromDTO(dto);
        return ResponseEntity.ok(toDTO(despesasService.update(id, despesas)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDespesa(@PathVariable Long id) {
        despesasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Converte Entidade para DTO
    private DespesasDto toDTO(Despesas despesas) {
        return new DespesasDto(
                despesas.getId(),
                despesas.getDescricao(),
                despesas.getValor(),
                despesas.getNomeDespesa(),
                despesas.getTotalDespesa()
        );
    }

    // Converte DTO para Entidade
    private Despesas fromDTO(DespesasDto dto) {
        return new Despesas(
                dto.id(),
                dto.descricao(),
                dto.valor(),
                null,  // Categoria - pode ser ajustada conforme necessário
                null,  // Usuário - pode ser ajustado conforme necessário
                dto.nomeDespesa(),
                dto.totalDespesa()
        );
    }
}
