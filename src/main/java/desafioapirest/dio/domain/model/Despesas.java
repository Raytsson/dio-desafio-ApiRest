package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DESPESA")
public class Despesas extends Transacoes {

    private String nomeDespesa;

    public Despesas(Long id, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, String nomeDespesa) {
        super(id, descricao, valor, categoria, usuario);
        this.nomeDespesa = nomeDespesa;
    }

    public Despesas() {
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }
}