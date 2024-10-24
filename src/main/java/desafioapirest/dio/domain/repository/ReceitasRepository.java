package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

}
