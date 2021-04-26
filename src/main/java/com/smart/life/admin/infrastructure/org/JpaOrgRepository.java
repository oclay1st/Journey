package com.smart.life.admin.infrastructure.org;

import com.smart.life.admin.domain.org.Org;
import com.smart.life.admin.domain.org.OrgRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaOrgRepository implements OrgRepository {

    private final OrgDAO orgDAO;

    public JpaOrgRepository(OrgDAO orgDAO) {
        this.orgDAO = orgDAO;
    }

    @Override
    public List<Org> findAll() {
        return orgDAO.findAll();
    }

    public List<Org> findAllByActiveTrue(){
        return orgDAO.findAllByActiveTrue();
    }
}
