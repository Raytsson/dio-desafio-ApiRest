package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesasService {

    @Autowired
    private DespesasRepository despesasRepository;

    public List<Despesas> findAll() {
        return despesasRepository.findAll();
    }

    public Optional<Despesas> findById(Long id) {
        return despesasRepository.findById(id);
    }

    public Despesas save(Despesas despesas) {
        return despesasRepository.save(despesas);
    }

    public void delete(Long id) {
        despesasRepository.deleteById(id);
    }

    public Despesas update(Long id, Despesas despesas) {
        despesas.setId(id);
        return despesasRepository.save(despesas);
    }
}
