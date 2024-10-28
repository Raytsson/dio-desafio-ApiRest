package desafioapirest.dio.controller;


import desafioapirest.dio.Dtos.CategoriaDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> getAllCategoria() {
        return categoriaService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(categoria -> ResponseEntity.ok(toDTO(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaDto createCategoria(@RequestBody CategoriaDto dto) {
        Categoria categoria = fromDTO(dto);
        return toDTO(categoriaService.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDto dto) {
        Categoria categoria = fromDTO(dto);
        return ResponseEntity.ok(toDTO(categoriaService.update(id, categoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Converte Entidade para DTO
    private CategoriaDto toDTO(Categoria categoria) {
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNome(),
                categoria.getLimiteOrcamento()
        );
    }

    // Converte DTO para Entidade
    private Categoria fromDTO(CategoriaDto dto) {
        return new Categoria(
                dto.id(),
                dto.nome(),
                dto.limiteOrcamento()
        );
    }
}
