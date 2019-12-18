package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Privilege;
import edu.mum.ea.socialmedia.repository.PrivilegeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PreAuthorize("hasRole('ROLE_MANAGE_PRIVILEGES')")
    @Override
    public Privilege add(Privilege privilege) {
        privilege.setName("ROLE_"+privilege.getName());
        return getPrivilegeRepository().save(privilege);
    }

    @Override
    public Optional<Privilege> findById(Long id) {
        return getPrivilegeRepository().findById(id);
    }

    @Override
    public List<Privilege> findAll() {
        return getPrivilegeRepository().findAll();
    }

    @PreAuthorize("hasRole('ROLE_MANAGE_PRIVILEGES')")
    @Override
    public void delete(Long id) {
        getPrivilegeRepository().deleteById(id);
    }
}
