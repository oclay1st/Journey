package com.smart.life.saas.domain.core.fleet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Fleet {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    private FleetModel model;

    @NonNull
    private Long typeId;

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
