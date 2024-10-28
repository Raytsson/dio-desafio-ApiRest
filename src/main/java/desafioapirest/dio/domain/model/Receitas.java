package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("RECEITAS")
public class Receitas extends Transacoes{

    private String nomeReceita;

    public Receitas(Long id, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, String nomeReceita) {
        super(id, descricao, valor, categoria, usuario);
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
