package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

}
