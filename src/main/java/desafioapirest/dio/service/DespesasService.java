package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.repository.DespesasRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesasService {

    @Autowired
    private DespesasRepository despesasRepository;

    public List<Despesas> findAll() {
        return despesasRepository.findAll();
    }

    public Despesas findById(Long id) {
        return despesasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com ID: " + id));
    }

    public Despesas save(Despesas despesas) {
        return despesasRepository.save(despesas);
    }

    public Despesas update(Long id, Despesas despesas) {
        findById(id);

        despesas.setId(id);
        return despesasRepository.save(despesas);
    }

    public void delete(Long id) {
        findById(id);
        despesasRepository.deleteById(id);
    }
}
