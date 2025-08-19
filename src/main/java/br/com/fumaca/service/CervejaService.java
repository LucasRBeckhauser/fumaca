package br.com.fumaca.service;

import br.com.fumaca.Mapper.CervejaMapper;
import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.model.Cerveja;
import br.com.fumaca.model.Cervejaria;
import br.com.fumaca.repository.CervejaRepository;
import br.com.fumaca.enterprise.CustomValidationException;
import br.com.fumaca.repository.UsuarioRepository;
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
    private UsuarioRepository.CervejariaRepository cervejariaRepository;

    @Autowired
    private CervejaMapper cervejaMapper;

    public CervejaResponseDTO salvar(CervejaRequestDTO dto) {
        // Validar se a cervejaria existe
        Cervejaria cervejaria = cervejariaRepository.findById(dto.getCervejariaId())
                .orElseThrow(() -> new CustomValidationException("Cervejaria não encontrada com ID: " + dto.getCervejariaId()));

        // Validar se já existe cerveja com mesmo nome na mesma cervejaria
        if (cervejaRepository.existsByNomeAndCervejariaId(dto.getNome(), dto.getCervejariaId())) {
            throw new CustomValidationException("Já existe uma cerveja com este nome para esta cervejaria");
        }

        Cerveja entity = cervejaMapper.toEntity(dto);
        entity.setCervejaria(cervejaria); // Setar a cervejaria manualmente

        Cerveja salvo = cervejaRepository.save(entity);
        return cervejaMapper.toDTO(salvo);
    }

    public List<CervejaResponseDTO> buscaTodos() {
        return cervejaRepository.findAll().stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CervejaResponseDTO> buscaPorCervejaria(Long cervejariaId) {
        // Validar se a cervejaria existe
        if (!cervejariaRepository.existsById(cervejariaId)) {
            throw new CustomValidationException("Cervejaria não encontrada com ID: " + cervejariaId);
        }

        return cervejaRepository.findByCervejariaId(cervejariaId).stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CervejaResponseDTO> buscaDisponiveis() {
        return cervejaRepository.findByDisponivelTrue().stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CervejaResponseDTO> buscaDestaques() {
        return cervejaRepository.findByDestaqueTrueAndDisponivelTrue().stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CervejaResponseDTO buscaPorId(Long id) {
        return cervejaRepository.findById(id)
                .map(cervejaMapper::toDTO)
                .orElseThrow(() -> new CustomValidationException("Cerveja não encontrada com ID: " + id));
    }

    public CervejaResponseDTO alterar(Long id, CervejaRequestDTO dtoAlterado) {
        Optional<Cerveja> encontrado = cervejaRepository.findById(id);

        if (encontrado.isPresent()) {
            Cerveja cerveja = encontrado.get();

            // Validar se está tentando mudar de cervejaria
            if (dtoAlterado.getCervejariaId() != null &&
                    !dtoAlterado.getCervejariaId().equals(cerveja.getCervejaria().getId())) {

                Cervejaria novaCervejaria = cervejariaRepository.findById(dtoAlterado.getCervejariaId())
                        .orElseThrow(() -> new CustomValidationException("Cervejaria não encontrada com ID: " + dtoAlterado.getCervejariaId()));
                cerveja.setCervejaria(novaCervejaria);
            }

            // Atualiza campos básicos se vierem preenchidos
            if (dtoAlterado.getNome() != null) {
                // Validar se o novo nome já existe na mesma cervejaria
                if (!dtoAlterado.getNome().equals(cerveja.getNome()) &&
                        cervejaRepository.existsByNomeAndCervejariaId(dtoAlterado.getNome(), cerveja.getCervejaria().getId())) {
                    throw new CustomValidationException("Já existe uma cerveja com este nome para esta cervejaria");
                }
                cerveja.setNome(dtoAlterado.getNome());
            }

            if (dtoAlterado.getDescricao() != null) cerveja.setDescricao(dtoAlterado.getDescricao());
            if (dtoAlterado.getTeorAlcoolico() != null) cerveja.setTeorAlcoolico(dtoAlterado.getTeorAlcoolico());
            if (dtoAlterado.getAmargor() != null) cerveja.setAmargor(dtoAlterado.getAmargor());
            if (dtoAlterado.getCor() != null) cerveja.setCor(dtoAlterado.getCor());
            if (dtoAlterado.getTipo() != null) cerveja.setTipo(dtoAlterado.getTipo());
            if (dtoAlterado.getPreco() != null) cerveja.setPreco(dtoAlterado.getPreco());
            if (dtoAlterado.getEstoqueCervejaria() != null) {
                cerveja.setEstoqueCervejaria(dtoAlterado.getEstoqueCervejaria());
                cerveja.setDisponivel(dtoAlterado.getEstoqueCervejaria() > 0);
            }
            if (dtoAlterado.getDestaque() != null) cerveja.setDestaque(dtoAlterado.getDestaque());

            // Ingredientes: lógica flexível (mantida da versão anterior)
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
                        .collect(Collectors.toList()));
            }

            // Remover ingredientes
            if (dtoAlterado.getIngredientesRemover() != null) {
                ingredientesAtuais.removeAll(dtoAlterado.getIngredientesRemover().stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList()));
            }

            // Atualiza a string final
            cerveja.setIngredientes(String.join(";", ingredientesAtuais));

            Cerveja salvo = cervejaRepository.save(cerveja);
            return cervejaMapper.toDTO(salvo);
        }

        throw new CustomValidationException("Cerveja não encontrada com ID: " + id);
    }

    public void atualizarEstoque(Long cervejaId, Integer novoEstoque) {
        Cerveja cerveja = cervejaRepository.findById(cervejaId)
                .orElseThrow(() -> new CustomValidationException("Cerveja não encontrada com ID: " + cervejaId));

        cerveja.setEstoqueCervejaria(novoEstoque);
        cerveja.setDisponivel(novoEstoque > 0);
        cerveja.setDataAtualizacaoEstoque(java.time.LocalDateTime.now());

        cervejaRepository.save(cerveja);
    }

    public void incrementarVendas(Long cervejaId, Integer quantidade) {
        Cerveja cerveja = cervejaRepository.findById(cervejaId)
                .orElseThrow(() -> new CustomValidationException("Cerveja não encontrada com ID: " + cervejaId));

        // Atualizar estoque
        int novoEstoque = cerveja.getEstoqueCervejaria() - quantidade;
        if (novoEstoque < 0) {
            throw new CustomValidationException("Estoque insuficiente para a cerveja ID: " + cervejaId);
        }

        cerveja.setEstoqueCervejaria(novoEstoque);
        cerveja.setDisponivel(novoEstoque > 0);

        // Incrementar contador de vendas
        cerveja.setVezesVendida(cerveja.getVezesVendida() + quantidade);

        cervejaRepository.save(cerveja);
    }

    public void remover(Long id) {
        if (!cervejaRepository.existsById(id)) {
            throw new CustomValidationException("Cerveja não encontrada com ID: " + id);
        }
        cervejaRepository.deleteById(id);
    }
}