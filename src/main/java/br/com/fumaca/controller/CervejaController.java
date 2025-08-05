package br.com.fumaca.controller;

import br.com.fumaca.controller.AbstractController;
import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.service.CervejaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cervejas")
@Tag(name = "Cervejas", description = "Operações relacionadas ao gerenciamento de cervejas")
public class CervejaController extends AbstractController {

    @Autowired
    private CervejaService service;

    @Operation(
            summary = "Cadastrar uma nova cerveja",
            description = "Cria uma nova cerveja com os dados informados. Os ingredientes devem ser separados por ';'.")
    @PostMapping
    public ResponseEntity<CervejaResponseDTO> create(@RequestBody CervejaRequestDTO dto) {
        CervejaResponseDTO salvo = service.salvar(dto);
        return ResponseEntity.created(URI.create("/cervejas/" + salvo.getId())).body(salvo);
    }


    @Operation(
            summary = "Listar todas as cervejas",
            description = "Retorna uma lista com todas as cervejas cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<CervejaResponseDTO>> findAll() {
        List<CervejaResponseDTO> lista = service.buscaTodos();
        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Buscar cerveja por ID",
            description = "Busca uma cerveja específica pelo ID fornecido"
    )
    @ApiResponse(responseCode = "200", description = "Cerveja encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    @GetMapping("{id}")
    public ResponseEntity<CervejaResponseDTO> findById(@PathVariable Long id) {
        CervejaResponseDTO dto = service.buscaPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Alterar cerveja",
            description = """
        Atualiza uma cerveja existente com os dados informados. 
        Permite alterar campos diretamente, adicionar ou remover ingredientes.
        """)
    @ApiResponse(responseCode = "200", description = "Cerveja atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    @PutMapping("{id}")
    public ResponseEntity<CervejaResponseDTO> update(@PathVariable Long id, @RequestBody CervejaRequestDTO dto) {
        CervejaResponseDTO alterado = service.alterar(id, dto);
        if (alterado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alterado);
    }


    @Operation(
            summary = "Excluir cerveja",
            description = "Remove uma cerveja do sistema com base no ID fornecido"
    )
    @ApiResponse(responseCode = "204", description = "Cerveja removida com sucesso")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
