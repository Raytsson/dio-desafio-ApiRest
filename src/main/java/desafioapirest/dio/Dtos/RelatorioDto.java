package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Despesas;
import desafioapirest.dio.domain.model.Receitas;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioDto(
        List<Receitas> receitas,
        List<Despesas> despesas,
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo
) {}