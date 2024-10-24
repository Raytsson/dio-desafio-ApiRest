package desafioapirest.dio.Dtos;

import desafioapirest.dio.domain.model.Usuario;

public record UsuarioDto(Long id, String nome, String email) {

    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public Usuario toModel() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        return usuario;
    }
}
