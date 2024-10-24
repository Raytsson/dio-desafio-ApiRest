package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Receitas;

import java.math.BigDecimal;

public record ReceitasDto(Long id, String descricao, BigDecimal valor, String nomeReceita, Double totalReceita) {

    public ReceitasDto(Receitas receitas) {
        this(receitas.getId(), receitas.getDescricao(), receitas.getValor(), receitas.getNomeReceita(), receitas.getTotaReceita());
    }

    public Receitas toModel() {        Receitas receitas = new Receitas();

        receitas.setId(this.id);
        receitas.setDescricao(this.descricao);
        receitas.setValor(this.valor);
        receitas.setNomeReceita(this.nomeReceita);
        receitas.setTotaReceita(this.totalReceita);
        return receitas;
    }
}
