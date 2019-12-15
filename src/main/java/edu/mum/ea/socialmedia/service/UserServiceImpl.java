package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.AssignRolesData;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User assignRoles(AssignRolesData data) {
        User user = getUserRepository().findById(data.getTargetUser()).get();
        user.setRoles(data.getRoles());
        user = getUserRepository().save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return getUserRepository().findAll();
    }

}
