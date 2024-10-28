package desafioapirest.dio.domain.model;

import jakarta.persistence.*;


@Entity(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    private Double limiteOrcamento;

    public Categoria() {
    }

    public Categoria(Long id, String nome,Double limiteOrcamento) {
        this.id = id;
        this.nome = nome;
        this.limiteOrcamento = limiteOrcamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLimiteOrcamento() {
        return limiteOrcamento;
    }

    public void setLimiteOrcamento(Double limiteOrcamento) {
        this.limiteOrcamento = limiteOrcamento;
    }
}
