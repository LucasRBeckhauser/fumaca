package br.com.fumaca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cerveja extends  EntityId{

    private String nome;

    private String descricao;

    private String teorAlcoolico; // ABV

    private String amargor; // IBU

    private String cor; // EBC

    @Enumerated(EnumType.STRING)
    private TipoCerveja tipo;

    private String ingredientes;

}
