package br.com.fumaca.dto;

import br.com.fumaca.model.usuario.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsuarioRequestDTO {

    @Schema(description = "Nome completo do usuário", example = "Lucas Roque Beckhauser")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "lucas@email.com")
    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @Schema(description = "Senha do usuário", example = "senhaSegura123")
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;

    @Schema(description = "Lista de roles do usuário", example = "[\"ROLE_ADMIN\", \"ROLE_CLIENTE\"]")
    @NotEmpty(message = "O usuário deve ter pelo menos uma role")
    private Set<Role> roles;
}
