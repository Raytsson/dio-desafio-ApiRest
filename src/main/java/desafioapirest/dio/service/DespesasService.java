package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.repository.DespesasRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.InvalidValueException;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Despesas> findByAnoAndMes(int ano, int mes) {
        return despesasRepository.findByAnoAndMes(ano, mes);
    }

    public Despesas save(Despesas despesas) {
        validateDespesa(despesas);
        return despesasRepository.save(despesas);
    }

    public Despesas update(Long id, Despesas despesas) {
        findById(id); // verifica se a despesa existe
        validateDespesa(despesas); // valida as regras de negócio
        despesas.setId(id);
        return despesasRepository.save(despesas);
    }

    public void delete(Long id) {
        findById(id);
        despesasRepository.deleteById(id);
    }

    public List<Despesas> findByMes(int ano, int mes) {
        return despesasRepository.findByAnoAndMes(ano, mes);
    }

    // Validações
    private void validateDespesa(Despesas despesas) {
        if (despesas.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidValueException("O valor da despesa não pode ser zero ou negativo.");
        }

        if (despesas.getCategoria() == null) {
            throw new InvalidValueException("A despesa deve ter uma categoria.");
        }

        if (despesas.getDataTransacao() == null) {
            throw new InvalidValueException("A data da transação não pode ser nula.");
        }
    }
}
