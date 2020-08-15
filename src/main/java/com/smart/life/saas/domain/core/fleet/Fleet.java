package com.smart.life.saas.domain.core.fleet;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Fleet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fleet_sequence")
    private Long id;

    private String number;

    @ManyToOne
    private FleetModel model;

    @NonNull
    @Min(1)
    private Integer capacity;

    @NonNull
    @Min(0)
    private Integer maxLuggage;

    private boolean active;

    @ElementCollection
    private List<String> images;

}
