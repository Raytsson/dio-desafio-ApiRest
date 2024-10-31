package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.repository.CategoriaRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.DuplicateNameException;
import desafioapirest.dio.service.exceptions.BusinesErrors.LimiteOrcamentoNotFoundException;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public Categoria save(Categoria categoria) {
        if (categoriaRepository.findByNome(categoria.getNome()).isPresent()) {
            throw new DuplicateNameException("Já existe uma categoria com o nome: " + categoria.getNome());
        }
        if (categoria.getLimiteOrcamento() == null) {
            throw new LimiteOrcamentoNotFoundException("O limite de orçamento não pode ser nulo.");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        Categoria existingCategoria = findById(id);

        if (categoriaRepository.findByNome(categoria.getNome()).isPresent() &&
                !existingCategoria.getNome().equals(categoria.getNome())) {
            throw new DuplicateNameException("Já existe uma categoria com o nome: " + categoria.getNome());
        }

        existingCategoria.setNome(categoria.getNome());
        existingCategoria.setLimiteOrcamento(categoria.getLimiteOrcamento());

        return categoriaRepository.save(existingCategoria);
    }

    public void delete(Long id) {
        findById(id);
        categoriaRepository.deleteById(id);
    }
}
