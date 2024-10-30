package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.ReceitasDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.service.CategoriaService;
import desafioapirest.dio.service.ReceitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<ReceitasDto> getAllReceita() {
        return receitasService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitasDto> getReceitaById(@PathVariable Long id) {
        Receitas receitas = receitasService.findById(id);
        return ResponseEntity.ok(toDTO(receitas));
    }

    @PostMapping
    public ResponseEntity<ReceitasDto> createReceita(@RequestBody ReceitasDto dto) {
        Receitas receitas = fromDTO(dto);
        Receitas savedReceita = receitasService.save(receitas);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(savedReceita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitasDto> updateReceita(@PathVariable Long id, @RequestBody ReceitasDto dto) {
        Receitas receitas = fromDTO(dto);
        Receitas updatedReceitas = receitasService.update(id, receitas);
        return ResponseEntity.ok(toDTO(updatedReceitas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        receitasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ReceitasDto toDTO(Receitas receitas) {
        return new ReceitasDto(
                receitas.getId(),
                receitas.getDescricao(),
                receitas.getValor(),
                receitas.getNomeReceita(),
                receitas.getCategoria() != null ? receitas.getCategoria().getId() : null,
                receitas.getDataTransacao() != null ? receitas.getDataTransacao().toString() : null
        );
    }

    private Receitas fromDTO(ReceitasDto dto) {
        Categoria categoria = categoriaService.findById(dto.categoriaId());

        return new Receitas(
                dto.id(),
                dto.descricao(),
                dto.valor(),
                dto.dataTransacao() != null ? LocalDate.parse(dto.dataTransacao()) : null,
                categoria,
                dto.nomeDespesa()
        );
    }
}
