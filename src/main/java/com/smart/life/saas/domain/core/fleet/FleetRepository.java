package com.smart.life.saas.domain.core.fleet;

import com.smart.life.kernel.CrudBaseRepository;

public interface FleetRepository extends CrudBaseRepository<Fleet, Long> {

    boolean existById(Long id);

    boolean existByNumberAndIdNot(String number, Long id);
}
