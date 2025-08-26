package br.com.fumaca.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {

    private Long clienteId;
    private Long cervejariaId;

    private List<ItemPedidoRequestDTO> itens;

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
