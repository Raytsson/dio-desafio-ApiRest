package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.domain.repository.ReceitasRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    public List<Receitas> findAll() {
        return receitasRepository.findAll();
    }

    public Receitas findById(Long id) {
        return receitasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com ID: " + id));
    }

    public Receitas save(Receitas receitas) {
        return receitasRepository.save(receitas);
    }

    public Receitas update(Long id, Receitas receitas) {
        findById(id);

        receitas.setId(id);
        return receitasRepository.save(receitas);
    }

    public void delete(Long id) {
        findById(id);
        receitasRepository.deleteById(id);
    }
}
