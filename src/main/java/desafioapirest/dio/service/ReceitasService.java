package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.domain.repository.ReceitasRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.InvalidValueException;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
    }

    public Receitas save(Receitas receitas) {
        validateReceita(receitas);
        return receitasRepository.save(receitas);
    }

    public Receitas update(Long id, Receitas receitas) {
        findById(id); // verifica se a receita existe
        validateReceita(receitas); // valida as regras de negócio
        receitas.setId(id);
        return receitasRepository.save(receitas);
    }

    public void delete(Long id) {
        findById(id);
        receitasRepository.deleteById(id);
    }

    public List<Receitas> findByMes(int ano, int mes) {
        return receitasRepository.findByAnoAndMes(ano, mes);
    }

    public List<Receitas> findByAnoAndMes(int ano, int mes) {
        return receitasRepository.findByAnoAndMes(ano, mes);
    }

    // Validações
    private void validateReceita(Receitas receitas) {
        if (receitas.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidValueException("O valor da receita não pode ser zero ou negativo.");
        }

        if (receitas.getDataTransacao() == null) {
            throw new InvalidValueException("A data da transação não pode ser nula.");
        }

        // Caso a categoria seja obrigatória para receitas no futuro
        if (receitas.getCategoria() == null) {
            throw new InvalidValueException("A receita deve ter uma categoria.");
        }
    }
}
