package br.com.fumaca.Mapper;

import br.com.fumaca.dto.IngredienteCreateDTO;
import br.com.fumaca.dto.IngredienteDTO;
import br.com.fumaca.model.Ingrediente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {
    Ingrediente toEntity(IngredienteCreateDTO dto);
    IngredienteDTO toDTO(Ingrediente entity);
}