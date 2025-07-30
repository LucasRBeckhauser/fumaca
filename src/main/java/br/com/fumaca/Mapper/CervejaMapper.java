package br.com.fumaca.Mapper;

import br.com.fumaca.dto.CervejaCreateDTO;
import br.com.fumaca.dto.CervejaDTO;
import br.com.fumaca.model.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { IngredienteMapper.class })
public interface CervejaMapper {
    Cerveja toEntity(CervejaCreateDTO dto);

    @Mapping(source = "ingredientes", target = "ingredientesBasicos")
    CervejaDTO toDTO(Cerveja entity);
}

