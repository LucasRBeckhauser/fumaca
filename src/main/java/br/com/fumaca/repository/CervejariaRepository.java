package br.com.fumaca.repository;

import br.com.fumaca.model.Cervejaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CervejariaRepository extends JpaRepository<Cervejaria, Long> {

    // Verifica se já existe cervejaria com mesmo CNPJ
    boolean existsByCnpj(String cnpj);

    // Verifica se já existe cervejaria com mesmo nome fantasia
    boolean existsByNomeFantasia(String nomeFantasia);

    // Busca cervejaria por CNPJ
    Optional<Cervejaria> findByCnpj(String cnpj);

    // Busca cervejarias por nome fantasia (busca parcial)
    List<Cervejaria> findByNomeFantasiaContainingIgnoreCase(String nome);

    // Busca cervejarias ativas
    List<Cervejaria> findByAtivoTrue();

    // Busca cervejarias por cidade
    List<Cervejaria> findByEnderecoCidade(String cidade);

    // Busca cervejarias por estado
    List<Cervejaria> findByEnderecoEstado(String estado);

    // Busca cervejarias por razao social (busca parcial)
    List<Cervejaria> findByRazaoSocialContainingIgnoreCase(String razaoSocial);

    // Busca cervejarias que entregam em um raio específico (exemplo simplificado)
    @Query("SELECT c FROM Cervejaria c WHERE c.raioEntrega >= :distancia AND c.ativo = true")
    List<Cervejaria> findByRaioEntregaGreaterThanEqual(@Param("distancia") Integer distancia);

    // Contagem de cervejarias ativas
    Long countByAtivoTrue();

    // Busca cervejarias com melhor avaliação (se tiver campo avaliação)
    List<Cervejaria> findByOrderByAvaliacaoDesc();

    // Busca cervejarias por responsável
    List<Cervejaria> findByResponsavelContainingIgnoreCase(String responsavel);
}