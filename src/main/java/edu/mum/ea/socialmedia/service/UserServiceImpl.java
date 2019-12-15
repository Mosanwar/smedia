package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.ClaimReposotry;
import edu.mum.ea.socialmedia.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ClaimService claimService;
    @Autowired
    private ClaimReposotry claimReposotry;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findFollowings(String email) {
        return userRepository.findFollowongByEmail(email);
    }

    @Override
    public void deleteFollowing(String userEmail, String followingEmail) {
        User user = userRepository.findByEmail(userEmail);
        Set<User> followings = user.getFollowings();
        followings.remove(userRepository.findByEmail(followingEmail));
        user.setFollowings(followings);
        userRepository.save(user);
    }

    @Override
    public User addFollowing(String userEmail, String followingEmail) {
        User user = userRepository.findByEmail(userEmail);
        Set<User> followings = user.getFollowings();
        followings.add(userRepository.findByEmail(followingEmail));
        user.setFollowings(followings);
        return userRepository.save(user);
    }


    @Override
    public User add(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getBlockedUsers() {
        return userRepository.findAllByActiveFalse();
    }
    @Override
    public Boolean deactivatUser(User user) {
        user.setBlocked(true);
        user.setActive(false);
        userRepository.save(user);
        emailService.sendMail(user);
        return true;
    }

    @Override
    public Boolean activate(Long id) {
        User user = userRepository.findById(id).get();
        user.setBlocked(false);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public ResponseEntity addClaim(User user, Claim claim) {
        return claimService.addClaim(user,claim);

    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public boolean isBlocked(Long userId) {
        User user = findUserById(userId);
        return user.getBlocked() || !user.getActive();
    }


}
