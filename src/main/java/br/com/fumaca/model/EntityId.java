package br.com.fumaca.model;

import jakarta.persistence.*;

import java.util.List;

@MappedSuperclass
public class EntityId {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cerveja_ingrediente",
            joinColumns = @JoinColumn(name = "cerveja_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientesBasicos;

}
