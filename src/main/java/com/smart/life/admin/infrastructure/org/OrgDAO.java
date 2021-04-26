package com.smart.life.admin.infrastructure.org;

import com.smart.life.admin.domain.org.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgDAO extends JpaRepository<Org, Long> {

    List<Org> findAllByActiveTrue();

}
