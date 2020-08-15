package com.smart.life.saas.web.fleet.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.smart.life.kernel.SimpleNameResource;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "fleet")
@Relation(collectionRelation = "fleets")
@EqualsAndHashCode(callSuper = false)
public class FleetResource extends RepresentationModel<FleetResource> {

    private Long id;

    private String number;

    private SimpleNameResource model;

    private Integer capacity;

    private Integer maxLuggage;

    private boolean active;

    private List<String> imageUrls;
}
