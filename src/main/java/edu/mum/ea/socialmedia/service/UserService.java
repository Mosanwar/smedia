package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.AssignRolesData;
import edu.mum.ea.socialmedia.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User findByEmail(String email);

    User add(User user);

    User assignRoles(AssignRolesData data);

    List<User> findAll();
}
