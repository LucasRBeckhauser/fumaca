package br.com.fumaca.dto;

import br.com.fumaca.model.TipoCerveja;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CervejaRequestDTO {
    private String nome;
    private String descricao;
    private String teorAlcoolico;
    private String amargor;
    private String cor;
    private TipoCerveja tipo;
    private String ingredientes;

    // ✅ NOVOS CAMPOS
    private Long cervejariaId; // ID da cervejaria
    private BigDecimal preco; // Preço no marketplace
    private Integer estoqueCervejaria; // Estoque inicial
    private Boolean destaque; // Se é destaque

    // Ingredientes a adicionar (opcional)
    private List<String> ingredientesAdicionar;

    // Ingredientes a remover (opcional)
    private List<String> ingredientesRemover;
}