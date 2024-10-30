package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("DESPESA")
public class Despesas extends Transacoes {

    private String nomeDespesa;

    public Despesas(Long id, String descricao, BigDecimal valor, LocalDate dataTransacao, Categoria categoria, String nomeDespesa) {
        super(id, descricao, valor, dataTransacao, categoria);
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