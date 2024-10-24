package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {

}
