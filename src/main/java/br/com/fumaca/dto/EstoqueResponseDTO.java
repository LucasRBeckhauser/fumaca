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

    @Schema(description = "ID do registro de estoque", example = "1")
    private Long id;

    @Schema(description = "Nome da cerveja", example = "IPA Artesanal")
    private String cervejaNome;

    @Schema(description = "Quantidade total do lote", example = "100")
    private Integer quantidadeLote;

    @Schema(description = "Quantidade atual disponível", example = "90")
    private Integer quantidadeAtual;

    @Schema(description = "Data de entrada", example = "2025-08-10")
    private LocalDate dataEntrada;

    @Schema(description = "Data de validade", example = "2025-12-31")
    private LocalDate dataValidade;

    @Schema(description = "Fornecedor", example = "Cervejaria Artesanal")
    private String fornecedor;

    @Schema(description = "Preço de custo unitário", example = "8.50")
    private BigDecimal precoCusto;

    @Schema(description = "Observações", example = "Lote promocional")
    private String observacoes;

    private Integer quantidadeSaida;

}