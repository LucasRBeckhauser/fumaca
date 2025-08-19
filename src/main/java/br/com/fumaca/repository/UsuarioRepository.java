package br.com.fumaca.repository;

import br.com.fumaca.model.Cervejaria;
import br.com.fumaca.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Repository
    interface CervejariaRepository extends JpaRepository <Cervejaria, Long> {
    }
}
