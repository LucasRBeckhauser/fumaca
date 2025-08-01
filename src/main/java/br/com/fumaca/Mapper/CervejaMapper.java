package br.com.fumaca.Mapper;

import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.model.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CervejaMapper {

    @Mapping(target = "ingredientes", expression = "java(splitIngredientes(entity.getIngredientes()))")
    CervejaResponseDTO toDTO(Cerveja entity);

    Cerveja toEntity(CervejaRequestDTO dto);

    default List<String> splitIngredientes(String ingredientes) {
        if (ingredientes == null || ingredientes.isBlank()) return List.of();
        return Arrays.stream(ingredientes.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}

