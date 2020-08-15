package com.smart.life.saas.domain.core.fleet;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fleet_model_sequence")
    private Long id;

    private String name;

    private boolean active;

    private String thumbImage;

}
