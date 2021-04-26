package com.smart.life.admin.domain.org;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Org {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private boolean active;

    @NotNull
    @NotEmpty
    @Column(name = "db_server")
    private String dbServer;

    @NotNull
    @NotEmpty
    @Column(name = "db_name")
    private String dbName;

    @NotNull
    @NotEmpty
    @Column(name = "db_username")
    private String dbUsername;

    @NotNull
    @NotEmpty
    @Column(name = "db_password")
    private String dbPassword;

    @NotNull
    @Column(name = "db_port")
    private Long dbPort;
}
