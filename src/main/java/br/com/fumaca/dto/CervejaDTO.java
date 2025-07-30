package br.com.fumaca.dto;

import br.com.fumaca.model.TipoCerveja;
import lombok.Data;

import java.util.List;

    @Data
    public class CervejaDTO {
        private Long id;
        private String nome;
        private String descricao;
        private double teorAlcoolico;
        private int amargor;
        private int cor;
        private TipoCerveja tipo;
        private List<IngredienteDTO> ingredientesBasicos;
    }

