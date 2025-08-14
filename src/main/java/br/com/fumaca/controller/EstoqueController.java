package br.com.fumaca.controller;

import br.com.fumaca.dto.EstoqueEntradaRequestDTO;
import br.com.fumaca.dto.EstoqueResponseDTO;
import br.com.fumaca.dto.EstoqueSaidaRequestDTO;
import br.com.fumaca.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque", description = "Operações de entrada, saída e consulta de estoque de cervejas")
public class EstoqueController extends AbstractController {

    @Autowired
    private EstoqueService service;

    @Operation(
            summary = "Registrar entrada de estoque",
            description = "Adiciona um novo lote de cerveja ao estoque com os dados informados"
    )
    @ApiResponse(responseCode = "201", description = "Entrada registrada com sucesso")
    @PostMapping("/entrada")
    public ResponseEntity<EstoqueResponseDTO> registrarEntrada(@Valid @RequestBody EstoqueEntradaRequestDTO dto) {
        EstoqueResponseDTO response = service.registrarEntrada(dto);
        return ResponseEntity.created(null).body(response);
    }

    @Operation(
            summary = "Registrar saída de estoque",
            description = "Retira uma quantidade de cerveja do estoque, respeitando a ordem de validade"
    )
    @ApiResponse(responseCode = "200", description = "Saída registrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Estoque insuficiente ou dados inválidos")
    @PostMapping("/saida")
    public ResponseEntity<EstoqueResponseDTO> registrarSaida(@Valid @RequestBody EstoqueSaidaRequestDTO dto) {
        EstoqueResponseDTO response = service.registrarSaida(dto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Consultar saldo disponível",
            description = "Retorna a quantidade total disponível em estoque para uma cerveja específica"
    )
    @ApiResponse(responseCode = "200", description = "Saldo retornado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    @GetMapping("/saldo/{cervejaId}")
    public ResponseEntity<Integer> consultarSaldo(@PathVariable Long cervejaId) {
        Integer saldo = service.getSaldoDisponivel(cervejaId);
        return ResponseEntity.ok(saldo);
    }
}
