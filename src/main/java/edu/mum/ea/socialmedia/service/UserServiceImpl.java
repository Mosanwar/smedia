package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

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

}
