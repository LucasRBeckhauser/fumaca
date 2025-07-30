package br.com.fumaca.repository;

import br.com.fumaca.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository <Ingrediente, Long> {

}
