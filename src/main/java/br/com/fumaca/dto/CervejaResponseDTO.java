package br.com.fumaca.dto;

import br.com.fumaca.model.TipoCerveja;
import lombok.Data;

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
    }


