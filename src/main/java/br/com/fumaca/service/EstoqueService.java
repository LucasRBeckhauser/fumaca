package br.com.fumaca.service;

import br.com.fumaca.dto.EstoqueEntradaRequestDTO;
import br.com.fumaca.dto.EstoqueResponseDTO;
import br.com.fumaca.dto.EstoqueSaidaRequestDTO;
import br.com.fumaca.enterprise.CustomValidationException;
import br.com.fumaca.model.Cerveja;
import br.com.fumaca.model.Estoque;
import br.com.fumaca.repository.CervejaRepository;
import br.com.fumaca.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private CervejaRepository cervejaRepository;


    // Registrar entrada de um novo lote
    public EstoqueResponseDTO registrarEntrada(EstoqueEntradaRequestDTO dto) {
        validarDataValidade(dto.getDataValidade()); // Valida se a data é futura
        Cerveja cerveja = buscarCervejaOuFalhar(dto.getCervejaId());

        Estoque estoque = Estoque.builder()
                .cerveja(cerveja)
                .quantidadeLote(dto.getQuantidadeLote())
                .quantidadeInicial(dto.getQuantidadeInicial())
                .dataEntrada(dto.getDataEntrada())
                .dataValidade(dto.getDataValidade())
                .fornecedor(dto.getFornecedor())
                .precoCusto(dto.getPrecoCusto())
                .observacoes(dto.getObservacoes())
                .build();

        estoqueRepository.save(estoque);
        return toResponseDTO(estoque);
    }

    // Registrar saída de estoque
    public EstoqueResponseDTO registrarSaida(EstoqueSaidaRequestDTO dto) {
        Cerveja cerveja = buscarCervejaOuFalhar(dto.getCervejaId());
        List<Estoque> lotesDisponiveis = estoqueRepository.findByCervejaIdOrderByDataValidadeAsc(dto.getCervejaId());

        int quantidadeRestante = dto.getQuantidadeSaida();
        for (Estoque lote : lotesDisponiveis) {
            if (quantidadeRestante <= 0) break;

            int quantidadeDisponivel = lote.getQuantidadeInicial();
            if (quantidadeDisponivel > 0) {
                int quantidadeRetirada = Math.min(quantidadeDisponivel, quantidadeRestante);
                lote.setQuantidadeInicial(quantidadeDisponivel - quantidadeRetirada);
                quantidadeRestante -= quantidadeRetirada;
                estoqueRepository.save(lote);
            }
        }

        if (quantidadeRestante > 0) {
            throw new CustomValidationException("Estoque insuficiente para a cerveja ID: " + dto.getCervejaId());
        }

        return EstoqueResponseDTO.builder()
                .cervejaNome(cerveja.getNome())
                .quantidadeSaida(dto.getQuantidadeSaida())
                .observacoes(dto.getObservacoes())
                .build();
    }

    // Consultar saldo disponível por cerveja
    public Integer getSaldoDisponivel(Long cervejaId) {
        buscarCervejaOuFalhar(cervejaId); // Valida se a cerveja existe
        return estoqueRepository.findByCervejaId(cervejaId).stream()
                .mapToInt(Estoque::getQuantidadeInicial)
                .sum();
    }


    private Cerveja buscarCervejaOuFalhar(Long cervejaId) {
        return cervejaRepository.findById(cervejaId)
                .orElseThrow(() -> new CustomValidationException("Cerveja não encontrada com ID: " + cervejaId));
    }

    private void validarDataValidade(LocalDate dataValidade) {
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new CustomValidationException("Data de validade não pode ser anterior à data atual");
        }
    }

    private EstoqueResponseDTO toResponseDTO(Estoque estoque) {
        return EstoqueResponseDTO.builder()
                .id(estoque.getId())
                .cervejaNome(estoque.getCerveja().getNome())
                .quantidadeLote(estoque.getQuantidadeLote())
                .quantidadeAtual(estoque.getQuantidadeInicial())
                .dataEntrada(estoque.getDataEntrada())
                .dataValidade(estoque.getDataValidade())
                .fornecedor(estoque.getFornecedor())
                .precoCusto(estoque.getPrecoCusto())
                .observacoes(estoque.getObservacoes())
                .build();
    }
}