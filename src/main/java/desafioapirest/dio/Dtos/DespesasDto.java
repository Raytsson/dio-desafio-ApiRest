package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Despesas;

import java.math.BigDecimal;


public record DespesasDto(Long id, String descricao, BigDecimal valor, String nomeDespesa) {

    // Método estático para converter Entidade para DTO
    public static DespesasDto fromEntity(Despesas despesas) {
        return new DespesasDto(
                despesas.getId(),
                despesas.getDescricao(),
                despesas.getValor(),
                despesas.getNomeDespesa()
        );
    }

    // Método para converter DTO para Entidade
    public Despesas toModel() {
        return new Despesas(
                this.id,
                this.descricao,
                this.valor,
                null,
                null,
                this.nomeDespesa
        );
    }
}
