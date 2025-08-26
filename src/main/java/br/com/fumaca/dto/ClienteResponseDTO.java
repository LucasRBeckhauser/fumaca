package br.com.fumaca.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteResponseDTO {
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

    private LocalDateTime dataCadastro;

}
