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
public class Cerveja extends EntityId {

    private String nome;
    private String descricao;
    private String teorAlcoolico; // ABV
    private String amargor; // IBU
    private String cor; // EBC

    @Enumerated(EnumType.STRING)
    private TipoCerveja tipo;
    private String ingredientes;

    // ✅ NOVOS CAMPOS PARA MARKETPLACE
    @ManyToOne
    @JoinColumn(name = "cervejaria_id")
    private Cervejaria cervejaria; // Relacionamento com a cervejaria

    private BigDecimal preco; // Preço de venda no marketplace

    private Integer estoqueCervejaria; // Estoque controlado pela cervejaria
    private Boolean disponivel; // Calculado baseado no estoque > 0

    private Boolean destaque; // Se aparece em destaque no site
    private Integer vezesVendida; // Contador de vendas para ranking

    private LocalDateTime dataAtualizacaoEstoque; // Última atualização
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataUltimaAtualizacao = LocalDateTime.now();
        if (disponivel == null) {
            disponivel = estoqueCervejaria != null && estoqueCervejaria > 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        dataUltimaAtualizacao = LocalDateTime.now();
        // Atualiza disponibilidade automaticamente
        this.disponivel = this.estoqueCervejaria != null && this.estoqueCervejaria > 0;
    }
}