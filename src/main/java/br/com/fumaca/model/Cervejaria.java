package br.com.fumaca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cervejaria extends EntityId {

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String responsavel;
    private String telefone;
    private String email;

    @Embedded
    private Endereco endereco;

    private BigDecimal comissaoMarketplace; // % de comissão
    private Integer raioEntrega; // Em km
    private BigDecimal taxaEntrega;
    private String tempoEntrega; // Ex: "24-48h"
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        if (ativo == null) {
            ativo = true;
        }
    }
}