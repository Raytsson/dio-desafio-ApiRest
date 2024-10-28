package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("RECEITAS")
public class Receitas extends Transacoes{

    private String nomeReceita;
    private Double TotaReceita;

    public Receitas(Long id, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, String nomeReceita, Double totaReceita) {
        super(id, descricao, valor, categoria, usuario);
        this.nomeReceita = nomeReceita;
        TotaReceita = totaReceita;
    }

    public Receitas() {
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    public Double getTotaReceita() {
        return TotaReceita;
    }

    public void setTotaReceita(Double totaReceita) {
        TotaReceita = totaReceita;
    }
}
