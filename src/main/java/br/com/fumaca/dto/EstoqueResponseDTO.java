package br.com.fumaca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueResponseDTO {

    @Schema(description = "ID do lote no estoque", example = "1")
    private Long id;

    @Schema(description = "ID da cerveja associada", example = "1")
    private Long cervejaId;

    @Schema(description = "Nome da cerveja", example = "Weissbier")
    private String nomeCerveja;

    @Schema(description = "Quantidade atual no lote", example = "100")
    private Integer quantidadeLote;

    @Schema(description = "Quantidade inicial do lote", example = "100")
    private Integer quantidadeInicial;

    @Schema(description = "Data de entrada do lote no estoque", example = "2025-08-08")
    private LocalDate dataEntrada;

    @Schema(description = "Data de validade do lote", example = "2025-12-31")
    private LocalDate dataValidade;

    @Schema(description = "Nome do fornecedor do lote", example = "Fornecedor XPTO")
    private String fornecedor;

    @Schema(description = "Preço de custo unitário do lote", example = "8.50")
    private BigDecimal precoCusto;

    @Schema(description = "Observações internas sobre o lote", example = "Lote com desconto especial")
    private String observacoes;
}
