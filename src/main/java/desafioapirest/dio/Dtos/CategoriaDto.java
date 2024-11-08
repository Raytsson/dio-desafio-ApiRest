package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Categoria;


public record CategoriaDto(Long id, String nome, Double limiteOrcamento) {

    public CategoriaDto(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getLimiteOrcamento());
    }

    public Categoria toModel() {
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNome(this.nome);
        categoria.setLimiteOrcamento(this.limiteOrcamento);
        return categoria;
    }
}
