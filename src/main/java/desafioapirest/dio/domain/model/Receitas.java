package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("RECEITAS")
public class Receitas extends Transacoes{

    private String nomeReceita;

    public Receitas(Long id, String descricao, BigDecimal valor, LocalDate dataTransacao, Categoria categoria,String nomeReceita) {
        super(id, descricao, valor, dataTransacao, categoria);
        this.nomeReceita = nomeReceita;
    }

    public Receitas(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    public Receitas() {
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }
}
