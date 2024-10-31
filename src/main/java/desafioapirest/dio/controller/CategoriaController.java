package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.CategoriaDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(toDTO(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@Valid @RequestBody CategoriaDto dto) {
        Categoria categoria = dto.toModel();
        Categoria savedCategoria = categoriaService.save(categoria);
        URI location = URI.create("/categorias/" + savedCategoria.getId());
        return ResponseEntity.created(location).body(toDTO(savedCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDto dto) {
        Categoria categoria = fromDTO(dto);
        return ResponseEntity.ok(toDTO(categoriaService.update(id, categoria)));
    }

    private CategoriaDto toDTO(Categoria categoria) {
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNome(),
                categoria.getLimiteOrcamento()
        );
    }

    private Categoria fromDTO(CategoriaDto dto) {
        return new Categoria(
                dto.id(),
                dto.nome(),
                dto.limiteOrcamento()
        );
    }
}
