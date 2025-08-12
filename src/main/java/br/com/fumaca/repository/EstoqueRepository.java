package br.com.fumaca.repository;

import br.com.fumaca.model.Estoque;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Long>  {

    List<Estoque> findByCervejaIdOrderByDataValidadeAsc(Long cervejaId);

    List<Estoque> findByCervejaId(Long cervejaId);
}
