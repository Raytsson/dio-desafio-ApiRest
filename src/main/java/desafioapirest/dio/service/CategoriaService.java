package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        if (categoriaRepository.findByNome(categoria.getNome()).isPresent()) {
            throw new RuntimeException("Já existe uma categoria com o nome: " + categoria.getNome());
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isEmpty()) {
            throw new RuntimeException("Categoria não encontrada");
        }

        if (categoriaRepository.findByNome(categoria.getNome()).isPresent() &&
                !categoriaRepository.findByNome(categoria.getNome()).get().getId().equals(id)) {
            throw new RuntimeException("Já existe uma categoria com o nome: " + categoria.getNome());
        }

        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }


}
