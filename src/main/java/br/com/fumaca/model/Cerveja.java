package br.com.fumaca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cerveja extends  EntityId{

    private String nome;

    private String descricao;

    private double teorAlcoolico; // ABV

    private int amargor; // IBU

    private int cor; // EBC

    @Enumerated(EnumType.STRING)
    private TipoCerveja tipo;



}
