package com.smart.life.kernel;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SimpleNameResource extends RepresentationModel<SimpleNameResource> {

    private Long id;

    private String name;
}
