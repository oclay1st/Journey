package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFleetRepository implements FleetRepository {

    private final FleetDAO fleetDAO;

    @Override
    public Fleet save(Fleet fleet) {
        return fleetDAO.save(fleet);
    }

    @Override
    public Page<Fleet> findAll(Pageable pageable) {
        return fleetDAO.findAll(pageable);
    }

    @Override
    public Optional<Fleet> findById(Long id) {
        return fleetDAO.findById(id);
    }

    @Override
    public boolean existById(Long id) {
        return fleetDAO.existsById(id);
    }

    @Override
    public boolean existByNumberAndIdNot(String number, Long id) {
        return fleetDAO.existsByNumberAndIdNot(number, id);
    }
}
