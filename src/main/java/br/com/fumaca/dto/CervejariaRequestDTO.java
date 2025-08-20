package br.com.fumaca.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CervejariaRequestDTO {


        private String razaoSocial;
        private String nomeFantasia;
        private String cnpj;
        private String responsavel;
        private String telefone;
        private String email;

        // Endereço embutido
        private String cep;
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;

        private BigDecimal comissaoMarketplace;
        private Integer raioEntrega;
        private BigDecimal taxaEntrega;
        private String tempoEntrega;
        private Boolean ativo;
    }


