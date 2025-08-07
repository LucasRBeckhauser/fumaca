package br.com.fumaca.controller;


import br.com.fumaca.dto.UsuarioRequestDTO;
import br.com.fumaca.dto.UsuarioResponseDTO;
import br.com.fumaca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Cria um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.criarUsuario(dto);
    }

    @Operation(summary = "Lista todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada")
    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @Operation(summary = "Atualiza um usuário pelo ID")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable UUID id,
                                        @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.atualizarUsuario(id, dto);
    }

    @Operation(summary = "Deleta um usuário pelo ID")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }
}
