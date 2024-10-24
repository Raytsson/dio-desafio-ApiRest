package desafioapirest.dio.domain.model;

import java.math.BigDecimal;

public class Despesas extends Transacoes {

    private String nomeDespesa;
    private Double TotalDespesa;

    public Despesas(Long id, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, String nomeDespesa, Double totalDespesa) {
        super(id, descricao, valor, categoria, usuario);
        this.nomeDespesa = nomeDespesa;
        TotalDespesa = totalDespesa;
    }

    public Despesas() {
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public Double getTotalDespesa() {
        return TotalDespesa;
    }

    public void setTotalDespesa(Double totalDespesa) {
        TotalDespesa = totalDespesa;
    }
}