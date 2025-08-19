package br.com.fumaca.dto;

import br.com.fumaca.model.TipoCerveja;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CervejaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String teorAlcoolico;
    private String amargor;
    private String cor;
    private TipoCerveja tipo;
    private List<String> ingredientes;

    // ✅ NOVOS CAMPOS
    private Long cervejariaId;
    private String cervejariaNome;
    private BigDecimal preco;
    private Integer estoqueCervejaria;
    private Boolean disponivel;
    private Boolean destaque;
    private Integer vezesVendida;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
}