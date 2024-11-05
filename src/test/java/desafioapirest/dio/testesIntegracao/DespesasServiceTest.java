package desafioapirest.dio.testesIntegracao;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.repository.DespesasRepository;
import desafioapirest.dio.service.DespesasService;
import desafioapirest.dio.service.exceptions.BusinesErrors.InvalidValueException;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DespesasServiceTest {

    @InjectMocks
    private DespesasService despesasService;

    @Mock
    private DespesasRepository despesasRepository;

    private Despesas despesa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração de uma despesa de teste
        Categoria categoria = new Categoria(1L, "Categoria Teste", 100.0);
        despesa = new Despesas(1L, "Descrição Teste", BigDecimal.valueOf(150.0), LocalDate.now(), categoria, "Nome Despesa");
    }

    @Test
    void shouldFindAllDespesas() {
        when(despesasRepository.findAll()).thenReturn(List.of(despesa));

        List<Despesas> despesasList = despesasService.findAll();

        assertEquals(1, despesasList.size());
        assertEquals("Nome Despesa", despesasList.get(0).getNomeDespesa());
    }

    @Test
    void shouldFindDespesaById() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.of(despesa));

        Despesas foundDespesa = despesasService.findById(1L);

        assertEquals("Nome Despesa", foundDespesa.getNomeDespesa());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenFindingNonExistentDespesaById() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            despesasService.findById(1L);
        });

        assertEquals("Despesa não encontrada com ID: 1", exception.getMessage());
    }

    @Test
    void shouldSaveDespesa() {
        when(despesasRepository.save(any())).thenReturn(despesa);

        Despesas savedDespesa = despesasService.save(despesa);

        assertNotNull(savedDespesa);
        assertEquals("Nome Despesa", savedDespesa.getNomeDespesa());
        verify(despesasRepository, times(1)).save(despesa);
    }

    @Test
    void shouldUpdateDespesa() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.of(despesa));
        when(despesasRepository.save(any())).thenReturn(despesa);

        Despesas updatedDespesa = despesasService.update(1L, despesa);

        assertEquals("Nome Despesa", updatedDespesa.getNomeDespesa());
        verify(despesasRepository, times(1)).save(despesa);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUpdatingNonExistentDespesa() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            despesasService.update(1L, despesa);
        });

        assertEquals("Despesa não encontrada com ID: 1", exception.getMessage());
    }

    @Test
    void shouldDeleteDespesa() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.of(despesa));
        doNothing().when(despesasRepository).deleteById(1L);

        despesasService.delete(1L);

        verify(despesasRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeletingNonExistentDespesa() {
        when(despesasRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            despesasService.delete(1L);
        });

        assertEquals("Despesa não encontrada com ID: 1", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidValueExceptionWhenSavingDespesaWithNegativeValue() {
        Despesas despesaNegativa = new Despesas(1L, "Descrição Teste", BigDecimal.valueOf(-150.0), LocalDate.now(), new Categoria(1L, "Categoria Teste", 100.0), "Nome Despesa");

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            despesasService.save(despesaNegativa);
        });

        assertEquals("O valor da despesa não pode ser zero ou negativo.", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidValueExceptionWhenSavingDespesaWithoutCategory() {
        Despesas despesaSemCategoria = new Despesas(1L, "Descrição Teste", BigDecimal.valueOf(150.0), LocalDate.now(), null, "Nome Despesa");

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            despesasService.save(despesaSemCategoria);
        });

        assertEquals("A despesa deve ter uma categoria.", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidValueExceptionWhenSavingDespesaWithoutTransactionDate() {
        Despesas despesaSemData = new Despesas(1L, "Descrição Teste", BigDecimal.valueOf(150.0), null, new Categoria(1L, "Categoria Teste", 100.0), "Nome Despesa");

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            despesasService.save(despesaSemData);
        });

        assertEquals("A data da transação não pode ser nula.", exception.getMessage());
    }
}
