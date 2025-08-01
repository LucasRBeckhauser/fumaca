package br.com.fumaca.dto;

import br.com.fumaca.model.TipoCerveja;
import lombok.Data;

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

    // Ingredientes a adicionar (opcional)
    private List<String> ingredientesAdicionar;

    // Ingredientes a remover (opcional)
    private List<String> ingredientesRemover;
}
