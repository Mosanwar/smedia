package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Role;
import edu.mum.ea.socialmedia.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @PreAuthorize("hasRole('ROLE_MANAGE_ROLES')")
    @Override
    public Role add(Role role) {
        return getRoleRepository().save(role);
    }

    @Override
    public Role findById(Long id) {
        return getRoleRepository().findById(id).get();
    }

    @Override
    public Role findByName(String name) {
        return getRoleRepository().findByName(name).get();
    }

    @Override
    public List<Role> findAll() {
        return getRoleRepository().findAll();
    }

    @PreAuthorize("hasRole('ROLE_MANAGE_ROLES')")
    @Override
    public Boolean deleteById(Long id) {
        getRoleRepository().deleteById(id);
        return true;
    }
}
