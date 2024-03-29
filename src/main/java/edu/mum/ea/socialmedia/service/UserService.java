package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.AssignRolesData;
import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User save(User user);
    User findByEmail(String email);
    List<User> findFollowings(String email);

    void deleteFollowing(String userEmail,String followingEmail);
    User addFollowing(String userEmail,String followingEmail);
    User add(User user);
    List<User> getBlockedUsers();
    Boolean deactivatUser(User user);
    Boolean activate(Long id);
    ResponseEntity addClaim(User user, Claim claim);
    User findUserById(Long userId);
    boolean isBlocked(Long userId);

    User findUserBytoken();

    User assignRoles(AssignRolesData data);

    List<User> findAll();
}
