package br.com.fumaca.service;

import br.com.fumaca.dto.CervejariaRequestDTO;
import br.com.fumaca.dto.CervejariaResponseDTO;
import br.com.fumaca.enterprise.CustomValidationException;
import br.com.fumaca.model.Cervejaria;
import br.com.fumaca.repository.CervejariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CervejariaService {

    @Autowired
    private CervejariaRepository cervejariaRepository;

    @Autowired
    private br.com.fumaca.mapper.CervejariaMapper cervejariaMapper;

    public CervejariaResponseDTO salvar(CervejariaRequestDTO dto) {
        if (cervejariaRepository.existsByNomeFantasia(dto.getNomeFantasia())) {
            throw new CustomValidationException("Já existe uma cervejaria com esse nome fantasia");
        }

        Cervejaria entity = cervejariaMapper.toEntity(dto);
        Cervejaria salvo = cervejariaRepository.save(entity);
        return cervejariaMapper.toDTO(salvo);
    }

    public List<CervejariaResponseDTO> buscaTodos() {
        return cervejariaRepository.findAll().stream()
                .map(cervejariaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CervejariaResponseDTO buscaPorId(Long id) {
        return cervejariaRepository.findById(id)
                .map(cervejariaMapper::toDTO)
                .orElseThrow(() -> new CustomValidationException("Cervejaria não encontrada com ID: " + id));
    }

    public CervejariaResponseDTO alterar(Long id, CervejariaRequestDTO dtoAlterado) {
        Optional<Cervejaria> encontrado = cervejariaRepository.findById(id);

        if (encontrado.isPresent()) {
            Cervejaria cervejaria = encontrado.get();

            if (dtoAlterado.getNomeFantasia() != null &&
                    !dtoAlterado.getNomeFantasia().equals(cervejaria.getNomeFantasia()) &&
                    cervejariaRepository.existsByNomeFantasia(dtoAlterado.getNomeFantasia())) {
                throw new CustomValidationException("Já existe uma cervejaria com esse nome fantasia");
            }

            cervejaria.setRazaoSocial(dtoAlterado.getRazaoSocial());
            cervejaria.setNomeFantasia(dtoAlterado.getNomeFantasia());
            cervejaria.setCnpj(dtoAlterado.getCnpj());
            cervejaria.setResponsavel(dtoAlterado.getResponsavel());
            cervejaria.setTelefone(dtoAlterado.getTelefone());
            cervejaria.setEmail(dtoAlterado.getEmail());
            cervejaria.setEndereco(cervejariaMapper.mapEndereco(dtoAlterado));
            cervejaria.setComissaoMarketplace(dtoAlterado.getComissaoMarketplace());
            cervejaria.setRaioEntrega(dtoAlterado.getRaioEntrega());
            cervejaria.setTaxaEntrega(dtoAlterado.getTaxaEntrega());
            cervejaria.setTempoEntrega(dtoAlterado.getTempoEntrega());
            cervejaria.setAtivo(dtoAlterado.getAtivo());

            Cervejaria salvo = cervejariaRepository.save(cervejaria);
            return cervejariaMapper.toDTO(salvo);
        }

        throw new CustomValidationException("Cervejaria não encontrada com ID: " + id);
    }

    public void remover(Long id) {
        if (!cervejariaRepository.existsById(id)) {
            throw new CustomValidationException("Cervejaria não encontrada com ID: " + id);
        }
        cervejariaRepository.deleteById(id);
    }
}
