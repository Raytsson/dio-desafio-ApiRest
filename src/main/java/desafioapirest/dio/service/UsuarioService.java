package desafioapirest.dio.service;

import desafioapirest.dio.domain.model.Usuario;
import desafioapirest.dio.domain.repository.UsuarioRepository;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario existingUsuario = findById(id);

        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setEmail(usuario.getEmail());
        return usuarioRepository.save(existingUsuario);
    }

    public void delete(Long id) {
        findById(id);
        usuarioRepository.deleteById(id);
    }
}
