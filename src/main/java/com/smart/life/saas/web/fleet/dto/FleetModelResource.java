package com.smart.life.saas.web.fleet.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "fleetModel")
@Relation(collectionRelation = "fleetModels")
@EqualsAndHashCode(callSuper = false)
public class FleetModelResource extends RepresentationModel<FleetModelResource> {

    private Long id;

    private String name;

    private boolean active;

    private String thumbImageUrl;

}
