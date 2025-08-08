package br.com.fumaca.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque extends EntityId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cerveja_id", nullable = false)
    private Cerveja cerveja;

    @Column(nullable = false)
    private Integer quantidadeLote;

    @Column(nullable = false)
    private Integer quantidadeInicial;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Column(length = 255)
    private String fornecedor;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoCusto;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
