package desafioapirest.dio.domain.repository;

import desafioapirest.dio.domain.model.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long> {
    @Query("SELECT r FROM Receitas r WHERE EXTRACT(YEAR FROM r.dataTransacao) = :ano AND EXTRACT(MONTH FROM r.dataTransacao) = :mes")
    List<Receitas> findByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);

}
