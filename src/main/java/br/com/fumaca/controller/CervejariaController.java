package br.com.fumaca.controller;

import br.com.fumaca.dto.CervejariaRequestDTO;
import br.com.fumaca.dto.CervejariaResponseDTO;
import br.com.fumaca.service.CervejariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cervejarias")
@Tag(name = "Cervejarias", description = "Operações relacionadas ao gerenciamento de cervejarias")
public class CervejariaController {

    @Autowired
    private CervejariaService service;

    @Operation(
            summary = "Cadastrar uma nova cervejaria",
            description = "Cria uma nova cervejaria com os dados informados. O campo 'especialidade' pode conter estilos separados por ponto e vírgula (;)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cervejaria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<CervejariaResponseDTO> create(@RequestBody CervejariaRequestDTO dto) {
        CervejariaResponseDTO salvo = service.salvar(dto);
        return ResponseEntity.created(URI.create("/cervejarias/" + salvo.getId())).body(salvo);
    }

    @Operation(
            summary = "Listar todas as cervejarias",
            description = "Retorna uma lista com todas as cervejarias cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<CervejariaResponseDTO>> findAll() {
        List<CervejariaResponseDTO> lista = service.buscaTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(
            summary = "Buscar cervejaria por ID",
            description = "Busca uma cervejaria específica pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cervejaria encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CervejariaResponseDTO> findById(@PathVariable Long id) {
        CervejariaResponseDTO dto = service.buscaPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Alterar cervejaria",
            description = """
        Atualiza uma cervejaria existente com os dados informados. 
        Permite modificar nome, localização e especialidades.
        """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cervejaria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CervejariaResponseDTO> update(@PathVariable Long id, @RequestBody CervejariaRequestDTO dto) {
        CervejariaResponseDTO alterado = service.alterar(id, dto);
        if (alterado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alterado);
    }

    @Operation(
            summary = "Excluir cervejaria",
            description = "Remove uma cervejaria do sistema com base no ID fornecido"
    )
    @ApiResponse(responseCode = "204", description = "Cervejaria removida com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
