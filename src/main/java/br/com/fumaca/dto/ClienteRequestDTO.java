package br.com.fumaca.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nomeCompleto;
    private String cpf;
    private String email;
    private String telefone;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

}
