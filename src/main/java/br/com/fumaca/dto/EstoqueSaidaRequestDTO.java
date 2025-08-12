package br.com.fumaca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueSaidaRequestDTO {

    @Schema(description = "ID da cerveja associada", example = "1")
    @NotNull(message = "O ID da cerveja é obrigatório")
    private Long cervejaId;

    @Schema(description = "Quantidade a ser retirada", example = "10")
    @NotNull(message = "A quantidade de saída é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private Integer quantidadeSaida;

    @Schema(description = "Observações da saída", example = "Retirada para evento XYZ")
    private String observacoes;
}