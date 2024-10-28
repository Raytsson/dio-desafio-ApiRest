package desafioapirest.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario") // Certifique-se de que "usuario" refere-se à propriedade na entidade Transacoes ou Despesas
    private List<Transacoes> transacoes; // Aqui você pode usar Transacoes ou Despesas, dependendo da relação

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, List<Transacoes> transacoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.transacoes = transacoes;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Transacoes> getTransacoes() { // Altere para corresponder ao novo tipo
        return transacoes;
    }

    public void setTransacoes(List<Transacoes> transacoes) {
        this.transacoes = transacoes;
    }
}
