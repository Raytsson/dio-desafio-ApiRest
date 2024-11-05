package desafioapirest.dio.testesIntegracao;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.domain.model.Relatorio;
import desafioapirest.dio.service.DespesasService;
import desafioapirest.dio.service.ReceitasService;
import desafioapirest.dio.service.RelatorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RelatorioServiceTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @Mock
    private ReceitasService receitasService;

    @Mock
    private DespesasService despesasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGerarRelatorio() {
        // Dados de teste
        int ano = 2024;
        int mes = 11;

        Categoria categoria = new Categoria(); // Você pode ajustar conforme a sua implementação
        categoria.setId(1L); // Se você tiver um ID específico para a categoria

        Receitas receita1 = new Receitas(1L, "Salário", BigDecimal.valueOf(5000), LocalDate.of(2024, 11, 15), categoria, "Salário do mês");
        Receitas receita2 = new Receitas(2L, "Venda", BigDecimal.valueOf(2000), LocalDate.of(2024, 11, 20), categoria, "Venda de produto");
        List<Receitas> receitas = Arrays.asList(receita1, receita2);

        Despesas despesa1 = new Despesas(1L, "Aluguel", BigDecimal.valueOf(1500), LocalDate.of(2024, 11, 5), categoria, "Aluguel de apartamento");
        Despesas despesa2 = new Despesas(2L, "Conta de Luz", BigDecimal.valueOf(300), LocalDate.of(2024, 11, 10), categoria, "Conta de luz do mês");
        List<Despesas> despesas = Arrays.asList(despesa1, despesa2);

        // Mockando os serviços de receitas e despesas
        when(receitasService.findByAnoAndMes(ano, mes)).thenReturn(receitas);
        when(despesasService.findByAnoAndMes(ano, mes)).thenReturn(despesas);

        // Chamando o método a ser testado
        Relatorio relatorio = relatorioService.gerarRelatorio(ano, mes);

        // Verificando os resultados
        assertEquals(BigDecimal.valueOf(7000), relatorio.getTotalReceitas());
        assertEquals(BigDecimal.valueOf(1800), relatorio.getTotalDespesas());
        assertEquals(BigDecimal.valueOf(5200), relatorio.getSaldo());
    }
}
