package desafioapirest.dio.domain.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tb_transacoes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transacao", discriminatorType = DiscriminatorType.STRING)
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate dataTransacao;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull
    private Categoria categoria;

    public Transacoes(Long id, String descricao, BigDecimal valor, LocalDate dataTransacao, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.categoria = categoria;
    }

    public Transacoes(Long id, String descricao, BigDecimal valor, LocalDate dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }

    public Transacoes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
