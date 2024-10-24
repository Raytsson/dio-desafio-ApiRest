package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsarioRepository extends JpaRepository<Usuario, Long> {

}
