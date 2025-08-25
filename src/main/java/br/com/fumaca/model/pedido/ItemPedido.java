package br.com.fumaca.model.pedido;

import br.com.fumaca.model.Cerveja;
import br.com.fumaca.model.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido extends EntityId {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cerveja_id")
    private Cerveja cerveja;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}
