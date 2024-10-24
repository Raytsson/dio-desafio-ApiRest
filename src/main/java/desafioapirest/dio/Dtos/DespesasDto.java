package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Despesas;

import java.math.BigDecimal;

public record DespesasDto(Long id, String descricao, BigDecimal valor, String nomeDespesa, Double totalDespesa) {

    public DespesasDto(Despesas despesas) {
        this(despesas.getId(), despesas.getDescricao(), despesas.getValor(), despesas.getNomeDespesa(), despesas.getTotalDespesa());
    }

    public Despesas toModel() {
        Despesas despesas = new Despesas();
        despesas.setId(this.id);
        despesas.setDescricao(this.descricao);
        despesas.setValor(this.valor);
        despesas.setNomeDespesa(this.nomeDespesa);
        despesas.setTotalDespesa(this.totalDespesa);
        return despesas;
    }
}
