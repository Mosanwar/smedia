package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.User;

public interface UserService {

    User save(User user);
    User findByEmail(String email);

    User add(User user);
}
