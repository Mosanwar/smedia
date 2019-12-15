package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User findByEmail(String email);
    List<User> findFollowings(String email);

    void deleteFollowing(String userEmail,String followingEmail);
    User addFollowing(String userEmail,String followingEmail);
    User add(User user);
}
