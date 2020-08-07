package com.smart.life.saas.domain.core.fleet;

import com.smart.life.kernel.CrudBaseRepository;

public interface FleetModelRepository extends CrudBaseRepository<FleetModel, Long> {

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existById(Long id);

}
