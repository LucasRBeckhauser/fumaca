package br.com.fumaca.repository;

import br.com.fumaca.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository <Estoque, Long>  {

}
