package br.com.fumaca.model.usuario;

import br.com.fumaca.model.EntityId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario extends EntityId {

    private String nome;
    private String email;
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
