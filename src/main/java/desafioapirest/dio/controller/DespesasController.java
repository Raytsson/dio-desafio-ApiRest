package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.DespesasDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.service.CategoriaService;
import desafioapirest.dio.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    private DespesasService despesasService;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<DespesasDto> getAllDespesas() {
        return despesasService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesasDto> getDespesaById(@PathVariable Long id) {
        Despesas despesas = despesasService.findById(id);
        return ResponseEntity.ok(toDTO(despesas));
    }

    @PostMapping
    public ResponseEntity<DespesasDto> createDespesa(@RequestBody DespesasDto dto) {
        Despesas despesas = fromDTO(dto);
        Despesas savedDespesa = despesasService.save(despesas);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(savedDespesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesasDto> updateDespesa(@PathVariable Long id, @RequestBody DespesasDto dto) {
        Despesas despesas = fromDTO(dto);
        Despesas updatedDespesa = despesasService.update(id, despesas);
        return ResponseEntity.ok(toDTO(updatedDespesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDespesa(@PathVariable Long id) {
        despesasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DespesasDto toDTO(Despesas despesas) {
        return new DespesasDto(
                despesas.getId(),
                despesas.getDescricao(),
                despesas.getValor(),
                despesas.getNomeDespesa(),
                despesas.getCategoria() != null ? despesas.getCategoria().getId() : null,
                despesas.getDataTransacao() != null ? despesas.getDataTransacao().toString() : null
        );
    }

    private Despesas fromDTO(DespesasDto dto) {
        Categoria categoria = categoriaService.findById(dto.categoriaId());

        return new Despesas(
                dto.id(),
                dto.descricao(),
                dto.valor(),
                dto.dataTransacao() != null ? LocalDate.parse(dto.dataTransacao()) : null,
                categoria,
                dto.nomeDespesa()
        );
    }
}
