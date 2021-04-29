package com.smart.life.saas.domain.core.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable{

    private static final long serialVersionUID = 190512204195025107L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    private Long id;

    @NotNull
    private UserRole name;

    @OneToMany
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", 
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
     )
    private Collection<Privilege> privileges;

    public Role(Long id, UserRole name){
        this.id = id;
        this.name = name;
    }

}
