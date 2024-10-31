package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.domain.model.Relatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private ReceitasService receitasService;

    @Autowired
    private DespesasService despesasService;

    public Relatorio gerarRelatorio(int ano, int mes) {
        List<Receitas> receitas = buscarReceitasPorMes(ano, mes);
        List<Despesas> despesas = buscarDespesasPorMes(ano, mes);

        BigDecimal totalReceitas = receitas.stream()
                .map(Receitas::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(Despesas::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Relatorio(receitas, despesas, totalReceitas, totalDespesas);
    }

    private List<Receitas> buscarReceitasPorMes(int ano, int mes) {
        return receitasService.findByAnoAndMes(ano, mes);
    }

    private List<Despesas> buscarDespesasPorMes(int ano, int mes) {
        return despesasService.findByAnoAndMes(ano, mes);
    }
}
