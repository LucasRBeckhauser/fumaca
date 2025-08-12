package br.com.fumaca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueEntradaRequestDTO {

    @Schema(description = "ID da cerveja associada ao lote", example = "1")
    @NotNull(message = "O ID da cerveja é obrigatório")
    private Long cervejaId;

    @Schema(description = "Quantidade total do lote", example = "100")
    @NotNull(message = "A quantidade do lote é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private Integer quantidadeLote;

    @Schema(description = "Quantidade inicial disponível", example = "100")
    @NotNull(message = "A quantidade inicial é obrigatória")
    @Min(value = 1, message = "A quantidade inicial deve ser pelo menos 1")
    private Integer quantidadeInicial;

    @Schema(description = "Data de entrada no estoque", example = "2025-08-10")
    @NotNull(message = "A data de entrada é obrigatória")
    private LocalDate dataEntrada;

    @Schema(description = "Data de validade do lote", example = "2025-12-31")
    @NotNull(message = "A data de validade é obrigatória")
    private LocalDate dataValidade;

    @Schema(description = "Nome do fornecedor", example = "Cervejaria Artesanal")
    @Size(max = 255, message = "O fornecedor deve ter no máximo 255 caracteres")
    private String fornecedor;

    @Schema(description = "Preço de custo unitário", example = "8.50")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço de custo deve ser maior que zero")
    private BigDecimal precoCusto;

    @Schema(description = "Observações adicionais", example = "Lote com desconto especial")
    private String observacoes;
}