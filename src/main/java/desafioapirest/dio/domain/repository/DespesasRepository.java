package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {
    @Query("SELECT d FROM Despesas d WHERE EXTRACT(YEAR FROM d.dataTransacao) = :ano AND EXTRACT(MONTH FROM d.dataTransacao) = :mes")
    List<Despesas> findByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);
}
