package com.smart.life.admin.infrastructure.org;

import com.smart.life.admin.domain.org.Org;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepositoryDAO extends JpaRepository<Org, Long> {
}
