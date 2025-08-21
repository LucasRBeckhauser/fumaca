package br.com.fumaca.repository;

import br.com.fumaca.model.Cervejaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejariaRepository extends JpaRepository<Cervejaria, Long> {

    boolean existsByNomeFantasia(String nomeFantasia);
}
