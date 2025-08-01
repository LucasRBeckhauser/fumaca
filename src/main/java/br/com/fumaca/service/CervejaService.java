package br.com.fumaca.service;

import br.com.fumaca.Mapper.CervejaMapper;
import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.model.Cerveja;
import br.com.fumaca.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CervejaService {

    @Autowired
    private CervejaRepository cervejaRepository;

    @Autowired
    private CervejaMapper cervejaMapper;


    public CervejaResponseDTO salvar(CervejaRequestDTO dto) {
        Cerveja entity = cervejaMapper.toEntity(dto);

        if (dto.getIngredientes() != null && !dto.getIngredientes().isBlank()) {
            List<String> listaIngredientes = Arrays.stream(dto.getIngredientes().split(";"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            entity.setIngredientes(String.join(";", listaIngredientes));
        }

        Cerveja salvo = cervejaRepository.save(entity);
        return cervejaMapper.toDTO(salvo);
    }

    public List<CervejaResponseDTO> buscaTodos() {
        return cervejaRepository.findAll().stream()
                .map(cervejaMapper::toDTO)
                .toList();
    }


    public CervejaResponseDTO buscaPorId(Long id) {
        return cervejaRepository.findById(id)
                .map(cervejaMapper::toDTO)
                .orElse(null);
    }


    public CervejaResponseDTO alterar(Long id, CervejaRequestDTO dtoAlterado) {
        Optional<Cerveja> encontrado = cervejaRepository.findById(id);

        if (encontrado.isPresent()) {
            Cerveja cerveja = encontrado.get();

            // Atualiza campos básicos se vierem preenchidos
            if (dtoAlterado.getNome() != null) cerveja.setNome(dtoAlterado.getNome());
            if (dtoAlterado.getDescricao() != null) cerveja.setDescricao(dtoAlterado.getDescricao());
            if (dtoAlterado.getTeorAlcoolico() != null) cerveja.setTeorAlcoolico(dtoAlterado.getTeorAlcoolico());
            if (dtoAlterado.getAmargor() != null) cerveja.setAmargor(dtoAlterado.getAmargor());
            if (dtoAlterado.getCor() != null) cerveja.setCor(dtoAlterado.getCor());
            if (dtoAlterado.getTipo() != null) cerveja.setTipo(dtoAlterado.getTipo());

            // Ingredientes: lógica flexível
            List<String> ingredientesAtuais = Arrays.stream(
                            Optional.ofNullable(cerveja.getIngredientes()).orElse("")
                                    .split(";"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            // Substituir completamente a lista (se vier como string)
            if (dtoAlterado.getIngredientes() != null) {
                ingredientesAtuais = Arrays.stream(dtoAlterado.getIngredientes().split(";"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            // Adicionar ingredientes
            if (dtoAlterado.getIngredientesAdicionar() != null) {
                ingredientesAtuais.addAll(dtoAlterado.getIngredientesAdicionar().stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList());
            }

            // Remover ingredientes
            if (dtoAlterado.getIngredientesRemover() != null) {
                ingredientesAtuais.removeAll(dtoAlterado.getIngredientesRemover().stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList());
            }

            // Atualiza a string final
            cerveja.setIngredientes(String.join(";", ingredientesAtuais));

            Cerveja salvo = cervejaRepository.save(cerveja);
            return cervejaMapper.toDTO(salvo);
        }

        return null;
    }


    public void remover(Long id) {
        cervejaRepository.deleteById(id);
    }
}
