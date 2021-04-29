package com.smart.life.saas.domain.core.user;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Privilege implements Serializable{

    private static final long serialVersionUID = 1905122041950151307L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_sequence")
    private Long id;

    @NonNull
    private String name;


    @ManyToMany(mappedBy = "assignedPrivileges")
    private Collection<User> users;


    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;


}
