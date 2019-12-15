package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Role;

import java.util.List;

public interface RoleService {

    Role add(Role role);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
    Boolean deleteById(Long id);
}
