package br.com.fumaca.model.pedido;

import br.com.fumaca.model.Cervejaria;
import br.com.fumaca.model.Cliente;
import br.com.fumaca.model.Endereco;
import br.com.fumaca.model.EntityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends EntityId {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cervejaria_id")
    private Cervejaria cervejaria;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    private BigDecimal valorTotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private Endereco enderecoEntrega;

    private LocalDateTime dataPedido;
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataPedido = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
        status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

}
