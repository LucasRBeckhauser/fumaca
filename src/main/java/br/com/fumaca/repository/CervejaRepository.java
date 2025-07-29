package br.com.fumaca.repository;

import br.com.fumaca.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejaRepository extends JpaRepository <Cerveja, Long> {

}
