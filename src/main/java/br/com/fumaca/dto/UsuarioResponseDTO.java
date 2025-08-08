package br.com.fumaca.dto;

import br.com.fumaca.model.usuario.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Dados retornados sobre um usuário")
public class UsuarioResponseDTO {

    @Schema(description = "Identificador único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nome completo do usuário", example = "Lucas Roque Beckhauser")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "lucas@email.com")
    private String email;

    @Schema(description = "Lista de papéis (roles) atribuídos ao usuário", example = "[\"ROLE_ADMIN\", \"ROLE_CLIENTE\"]")
    private Set<Role> roles;

    public UsuarioResponseDTO(UUID id, String nome, String email, Set<Role> roles) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.roles = roles;
    }

}
