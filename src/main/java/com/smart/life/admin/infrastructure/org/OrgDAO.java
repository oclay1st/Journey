package com.smart.life.admin.infrastructure.org;

import com.smart.life.admin.domain.org.Org;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgDAO extends JpaRepository<Org, Long> {
}
