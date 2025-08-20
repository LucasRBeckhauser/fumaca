package br.com.fumaca.repository;

import br.com.fumaca.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Long> {

    // Verifica se já existe cerveja com mesmo nome na mesma cervejaria
    boolean existsByNomeAndCervejariaId(String nome, Long cervejariaId);

    // Busca todas as cervejas de uma cervejaria específica
    List<Cerveja> findByCervejariaId(Long cervejariaId);

    // Busca cervejas disponíveis (estoque > 0)
    List<Cerveja> findByDisponivelTrue();

    // Busca cervejas em destaque e disponíveis
    List<Cerveja> findByDestaqueTrueAndDisponivelTrue();
}
