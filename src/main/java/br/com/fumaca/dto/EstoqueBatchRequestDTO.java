package br.com.fumaca.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueBatchRequestDTO {

    @NotEmpty(message = "A lista de lotes não pode ser vazia")
    @Valid
    private List<EstoqueRequestDTO> lotes;
}
