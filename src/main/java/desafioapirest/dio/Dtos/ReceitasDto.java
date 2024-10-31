package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.domain.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitasDto(Long id, String descricao, BigDecimal valor, String nomeReceitas, Long categoriaId,String dataTransacao) {

    public Receitas toModel(Categoria categoria, Usuario usuario) {
        return new Receitas(
                this.id,
                this.descricao,
                this.valor,
                LocalDate.parse(this.dataTransacao),
                categoria,
                this.nomeReceitas
        );
    }
}
