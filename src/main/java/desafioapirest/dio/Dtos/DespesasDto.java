package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Despesas;

import java.math.BigDecimal;
import java.time.LocalDate;


public record DespesasDto(Long id, String descricao, BigDecimal valor, String nomeDespesa, Long categoriaId, String dataTransacao) {

    public Despesas toModel(Categoria categoria) {
        return new Despesas(
                this.id,
                this.descricao,
                this.valor,
                LocalDate.parse(this.dataTransacao),
                categoria,
                this.nomeDespesa
        );
    }
}