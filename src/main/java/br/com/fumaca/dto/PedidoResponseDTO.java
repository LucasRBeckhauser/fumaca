package br.com.fumaca.dto;

import br.com.fumaca.model.pedido.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {

    private Long id;

    private ClienteResponseDTO cliente;
    private CervejariaResponseDTO cervejaria;

    private List<ItemPedidoResponseDTO> itens;

    private BigDecimal valorTotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorFinal;

    private StatusPedido status;

    private LocalDateTime dataPedido;
}
