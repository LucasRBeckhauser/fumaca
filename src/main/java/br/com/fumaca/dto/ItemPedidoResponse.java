package br.com.fumaca.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoResponse {

    private Long id;
    private String nomeCerveja;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}
