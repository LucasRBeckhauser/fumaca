package br.com.fumaca.repository;

import br.com.fumaca.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    // Busca cervejas por tipo e disponibilidade
    List<Cerveja> findByTipoAndDisponivelTrue(String tipo);

    // Busca cervejas por cervejaria e disponibilidade
    List<Cerveja> findByCervejariaIdAndDisponivelTrue(Long cervejariaId);

    // Busca cervejas com estoque baixo (para alertas)
    List<Cerveja> findByEstoqueCervejariaLessThanAndDisponivelTrue(Integer estoqueMinimo);

    // Busca cervejas por nome (busca parcial)
    List<Cerveja> findByNomeContainingIgnoreCaseAndDisponivelTrue(String nome);

    // Busca as cervejas mais vendidas (para ranking)
    List<Cerveja> findTop10ByOrderByVezesVendidaDesc();

    // Busca cervejas por faixa de preço
    List<Cerveja> findByPrecoBetweenAndDisponivelTrue(BigDecimal precoMin, BigDecimal precoMax);

    // Busca cerveja pelo SKU (se tiver)
    Optional<Cerveja> findBySku(String sku);

    // Contagem de cervejas por cervejaria
    Long countByCervejariaId(Long cervejariaId);

    // Soma do estoque total por cervejaria
    @Query("SELECT SUM(c.estoqueCervejaria) FROM Cerveja c WHERE c.cervejaria.id = :cervejariaId")
    Integer sumEstoqueByCervejariaId(@Param("cervejariaId") Long cervejariaId);

    // Busca cervejas que precisam ser atualizadas (estoque antigo)
    List<Cerveja> findByDataAtualizacaoEstoqueBefore(LocalDateTime dataLimite);
}