package br.com.fumaca.service;

import br.com.fumaca.Mapper.CervejaMapper;
import br.com.fumaca.dto.CervejaCreateDTO;
import br.com.fumaca.dto.CervejaDTO;
import br.com.fumaca.model.Cerveja;
import br.com.fumaca.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CervejaService {

    @Autowired
    private CervejaRepository cervejaRepository;

    @Autowired
    private CervejaMapper cervejaMapper;

    public CervejaDTO salvar(CervejaCreateDTO dto) {
        Cerveja entity = cervejaMapper.toEntity(dto);
        Cerveja salvo = cervejaRepository.save(entity);
        return cervejaMapper.toDTO(salvo);
    }

    public List<CervejaDTO> buscaTodos() {
        return cervejaRepository.findAll()
                .stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CervejaDTO buscaPorId(Long id) {
        return cervejaRepository.findById(id)
                .map(cervejaMapper::toDTO)
                .orElse(null);
    }

    public CervejaDTO alterar(Long id, CervejaCreateDTO dtoAlterado) {
        Optional<Cerveja> encontrado = cervejaRepository.findById(id);
        if (encontrado.isPresent()) {
            Cerveja atualizado = cervejaMapper.toEntity(dtoAlterado);
            atualizado.setId(id); // mantém ID original
            Cerveja salvo = cervejaRepository.save(atualizado);
            return cervejaMapper.toDTO(salvo);
        }
        return null;
    }

    public void remover(Long id) {
        cervejaRepository.deleteById(id);
    }
}
