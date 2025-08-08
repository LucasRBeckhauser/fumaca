package br.com.fumaca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueRequestDTO {

    @Schema(description = "ID da cerveja associada ao lote", example = "1")
    @NotNull(message = "O id da cerveja é obrigatório")
    private Long cervejaId;

    @Schema(description = "Quantidade atual no lote", example = "100")
    @NotNull(message = "A quantidade do lote é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    private Integer quantidadeLote;

    @Schema(description = "Quantidade inicial do lote", example = "100")
    @NotNull(message = "A quantidade inicial é obrigatória")
    @Min(value = 1, message = "Quantidade inicial deve ser pelo menos 1")
    private Integer quantidadeInicial;

    @Schema(description = "Data de entrada do lote no estoque", example = "2025-08-08")
    @NotNull(message = "A data de entrada é obrigatória")
    private LocalDate dataEntrada;

    @Schema(description = "Data de validade do lote", example = "2025-12-31")
    private LocalDate dataValidade;

    @Schema(description = "Nome do fornecedor do lote", example = "Fornecedor XPTO")
    @Size(max = 255, message = "Nome do fornecedor deve ter no máximo 255 caracteres")
    private String fornecedor;

    @Schema(description = "Preço de custo unitário do lote", example = "8.50")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço de custo deve ser maior que zero")
    private BigDecimal precoCusto;

    @Schema(description = "Observações internas sobre o lote", example = "Lote com desconto especial")
    private String observacoes;
}
