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

    private double teorAlcoolico; // ABV

    private int amargor; // IBU

    private int cor; // EBC

    @Enumerated(EnumType.STRING)
    private TipoCerveja tipo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cerveja_id")
    private List<Ingrediente> ingredientes;

}
