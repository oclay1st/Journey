package com.smart.life.saas.domain.core.fleet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleetModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean active;

    private String thumbImage;

}
