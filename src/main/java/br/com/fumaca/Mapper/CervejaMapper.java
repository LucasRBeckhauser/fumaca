package br.com.fumaca.Mapper;

import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.model.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CervejaMapper {

    @Mapping(target = "ingredientes", expression = "java(splitIngredientes(entity.getIngredientes()))")
    @Mapping(target = "cervejariaId", source = "entity.cervejaria.id")
    @Mapping(target = "cervejariaNome", source = "entity.cervejaria.nomeFantasia")
    CervejaResponseDTO toDTO(Cerveja entity);

    @Mapping(target = "cervejaria", ignore = true) // Será setado manualmente no service
    @Mapping(target = "ingredientes", expression = "java(joinIngredientes(dto.getIngredientes(), dto.getIngredientesAdicionar(), dto.getIngredientesRemover()))")
    @Mapping(target = "disponivel", expression = "java(calcularDisponibilidade(dto.getEstoqueCervejaria()))")
    @Mapping(target = "vezesVendida", ignore = true) // Inicializado como 0
    @Mapping(target = "dataCriacao", ignore = true) // Será setado automaticamente
    @Mapping(target = "dataUltimaAtualizacao", ignore = true) // Será setado automaticamente
    @Mapping(target = "dataAtualizacaoEstoque", ignore = true) // Será setado automaticamente
    Cerveja toEntity(CervejaRequestDTO dto);

    default List<String> splitIngredientes(String ingredientes) {
        if (ingredientes == null || ingredientes.isBlank()) return List.of();
        return Arrays.stream(ingredientes.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    default String joinIngredientes(String ingredientes, List<String> ingredientesAdicionar, List<String> ingredientesRemover) {
        // Lista base a partir do campo ingredientes
        List<String> listaBase = (ingredientes != null && !ingredientes.isBlank()) ?
                Arrays.stream(ingredientes.split(";"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList()) :
                new ArrayList<>();

        // Adicionar novos ingredientes se fornecidos
        if (ingredientesAdicionar != null) {
            listaBase.addAll(ingredientesAdicionar.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList()));
        }

        // Remover ingredientes se fornecidos
        if (ingredientesRemover != null) {
            listaBase.removeAll(ingredientesRemover.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList()));
        }

        // Remover duplicatas e retornar como string
        return listaBase.stream()
                .distinct()
                .collect(Collectors.joining(";"));
    }

    default Boolean calcularDisponibilidade(Integer estoqueCervejaria) {
        return estoqueCervejaria != null && estoqueCervejaria > 0;
    }
}