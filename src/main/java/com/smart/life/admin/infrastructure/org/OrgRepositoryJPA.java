package com.smart.life.admin.infrastructure.org;

import com.smart.life.admin.domain.org.Org;
import com.smart.life.admin.domain.org.OrgRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrgRepositoryJPA implements OrgRepository {

    private final OrgRepositoryDAO orgRepositoryDAO;

    public OrgRepositoryJPA(OrgRepositoryDAO orgRepositoryDAO) {
        this.orgRepositoryDAO = orgRepositoryDAO;
    }

    @Override
    public List<Org> findAll() {
        return orgRepositoryDAO.findAll();
    }
}
